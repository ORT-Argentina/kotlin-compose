package com.rickAndMorty.shared.infraestructure

import com.rickAndMorty.shared.infraestructure.characters.CharactersApi
import com.rickAndMorty.shared.model.Character
import com.rickAndMorty.shared.model.Characters
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharacterImpl : Characters {
    private val retrofit: Retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://rickandmortyapi.com/api/")
        .build()
    private val api = retrofit.create(CharactersApi::class.java)

    override suspend fun getAll(): List<Character>? {
        val response = api.getCharacters()
        return if (response.isSuccessful) {
            val result = response.body()?.toModel()
            if (result != null) {
                listOf(Character("Not Found character", "Dead", "")) + result
            } else {
                result
            }
        } else {
            null
        }
    }
}
