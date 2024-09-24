package com.rickAndMorty.list.feature.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.rickAndMorty.shared.model.Character

class ListPresenterPreview(
    characters: List<Character>? = null,
): ListPresenter {
    override val characters: State<List<Character>?> = mutableStateOf(characters)
}
