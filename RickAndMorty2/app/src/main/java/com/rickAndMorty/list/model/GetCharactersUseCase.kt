package com.rickAndMorty.list.model

import com.rickAndMorty.shared.model.Character
import com.rickAndMorty.shared.model.Characters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCharactersUseCase(
    private val characters: Characters,
) {
    suspend fun execute(): List<Character>? = withContext(Dispatchers.IO) {
        characters.getAll()
    }
}
