package com.rickAndMorty.shared.infraestructure.characters

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HEAD
import retrofit2.http.Path

interface CharactersApi {

    @GET("character")
    suspend fun getCharacters(): Response<CharactersResponse>

    @GET("location")
    suspend fun getLocation(): Response<CharactersResponse>
}
