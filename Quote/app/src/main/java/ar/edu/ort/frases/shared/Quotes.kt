package ar.edu.ort.frases.shared

import ar.edu.ort.frases.model.QuoteApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface QuotesApi {
        @GET("v1/quotes")
        suspend fun getQuotes(@Header("X-Api-Key") token: String): Response<List<QuoteApi>>
}