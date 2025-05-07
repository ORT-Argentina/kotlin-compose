package ar.edu.ort.frases.shared

import ar.edu.ort.frases.model.DogApi
import ar.edu.ort.frases.model.QuoteApi
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface QuotesApi {
        @GET("v1/quotes")
        suspend fun getQuotes(): Response<List<QuoteApi>>

        @GET( "/v1/dogs")
        suspend fun getDogByName(): Response<List<DogApi>>
}