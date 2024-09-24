package com.rickAndMorty.shared.infraestructure.characters

import retrofit2.Response
import retrofit2.http.GET

interface CharactersApi {
    @GET("character")
    suspend fun getCharacters(): Response<CharactersResponse>
}
