package com.rickAndMorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.rickAndMorty.list.feature.presentation.ListViewModel
import com.rickAndMorty.list.feature.ui.CharactersList
import com.rickAndMorty.list.model.GetCharactersUseCase
import com.rickAndMorty.shared.infraestructure.CharacterImpl
import com.rickAndMorty.ui.theme.RickAndMortyTheme

class MainActivity : ComponentActivity() {
    private val presenter = ListViewModel(GetCharactersUseCase(CharacterImpl()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.setup()

        enableEdgeToEdge()
        setContent {
            RickAndMortyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CharactersList(presenter, Modifier.padding(innerPadding))
                }
            }
        }
    }
}
