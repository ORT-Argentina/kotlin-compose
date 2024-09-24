package com.rickAndMorty.list.feature.presentation

import androidx.compose.runtime.State
import com.rickAndMorty.shared.model.Character

interface ListPresenter {
    val characters: State<List<Character>?>
}
