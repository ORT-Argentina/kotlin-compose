package ar.edu.ort.challenge.holamundo.ui.componets

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ar.edu.ort.challenge.holamundo.R
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppHolaMundoTopBar(){
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(stringResource(R.string.app_name))
        }
    )
}

@Preview
@Composable
fun AppHolaMundoTopBarPreview() {
    AppHolaMundoTopBar()
}