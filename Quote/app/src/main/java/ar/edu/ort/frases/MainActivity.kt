package ar.edu.ort.frases

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ar.edu.ort.frases.helpers.QuoteRetrofit
import ar.edu.ort.frases.shared.GetServiceQuotes
import ar.edu.ort.frases.ui.theme.FrasesTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels { MainViewModel.provideFactory(
        GetServiceQuotes(QuoteRetrofit())
    ) }

    override fun onCreate(savedInstanceState: Bundle?) {

        viewModel.loadQuotes()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FrasesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        quote = viewModel.Quote.value,
                        author = viewModel.Author.value,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(quote: String,  author: String, modifier: Modifier = Modifier) {
    Text(
        text = quote,
        modifier = modifier
    )
    Text(
        text = author,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FrasesTheme {
        Greeting("Quote...", "Author")
    }
}