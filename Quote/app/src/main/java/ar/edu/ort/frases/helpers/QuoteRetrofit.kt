package ar.edu.ort.frases.helpers

import ar.edu.ort.frases.model.Quote
import ar.edu.ort.frases.shared.IServiceQuotes
import ar.edu.ort.frases.shared.QuotesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class QuoteRetrofit : IServiceQuotes {

    val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    var client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val retrofit: Retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl("https://api.api-ninjas.com/")
        .build()

    private val api = retrofit.create(QuotesApi::class.java)

    override suspend fun getQuotes(): List<Quote>? {

        val response = api.getQuotes("definir_token")

        return if (response.isSuccessful) {
            val result = response.body()?.map {
                Quote(
                    quote = it.quote,
                    author = it.author,
                )
            }
            result
        } else {
            emptyList()
        }
    }
}
