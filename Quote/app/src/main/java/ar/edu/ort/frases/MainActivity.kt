package ar.edu.ort.frases

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.ort.frases.core.Config
import ar.edu.ort.frases.di.NetworkModule.client
import ar.edu.ort.frases.shared.QuotesApi
import ar.edu.ort.frases.ui.theme.FrasesTheme
import ar.edu.ort.frases.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel.loadQuotes()

        enableEdgeToEdge()
        setContent {
            FrasesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Quote(
                        quote = viewModel.Quote.value,
                        author = viewModel.Author.value,
                        category = viewModel.Category.value,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Quote(quote: String,  author: String, category: String, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = quote,
            modifier = modifier
        )
        Text(
            text = author,
            modifier = modifier
        )
        Text(
            text = category,
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FrasesTheme {
        Quote("Quote...", "Author", "Category")
    }
}