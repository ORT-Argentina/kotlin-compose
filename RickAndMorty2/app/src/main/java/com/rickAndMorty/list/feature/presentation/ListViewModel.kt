package com.rickAndMorty.list.feature.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickAndMorty.list.model.GetCharactersUseCase
import com.rickAndMorty.shared.model.Character
import kotlinx.coroutines.launch

class ListViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
): ViewModel(), ListPresenter {
    private val _characters = mutableStateOf<List<Character>?>(null)
    override val characters: State<List<Character>?> = _characters

    fun setup() {
        viewModelScope.launch {
            _characters.value = getCharactersUseCase.execute()
        }
    }
}