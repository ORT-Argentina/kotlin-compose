package com.rickAndMorty.shared.infraestructure.characters

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HEAD

interface CharactersApi {
    @GET("api/character")
    suspend fun getCharacters(): Response<CharactersResponse>
}
