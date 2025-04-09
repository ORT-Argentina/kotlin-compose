package ar.edu.ort.holamundo.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.ort.holamundo.ui.theme.HolaMundoTheme


@Preview(
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Primer Preview"
)
@Composable
fun AppHolaMundo() {
    HolaMundoTheme {
        AppHolaMundoScaffold()
    }
}

@Composable
fun AppHolaMundoScaffold(){
    Scaffold(modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {  }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->

        Text(
            text = "Hola Mundo!",
            modifier = Modifier
                //.padding(innerPadding)
                .padding(16.dp)
        )

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Click me!")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ){
        Text(

            text = "Hello $name!",
        )
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Click me!")
        }
    }
}


@Preview( name = "Otra pantalla", showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO)
@Composable
fun hola(){
    Text(
        text = "Hola Mundo!",
        modifier = Modifier
            .padding(16.dp)
    )
}


@Composable
fun ButtonGenic(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = text)
    }
}

@Preview( name = "Genic Button", showBackground = true)
@Composable
fun PreviewGenericButton(){
    ButtonGenic("Sign Up", onClick = { /*TODO*/ })
}