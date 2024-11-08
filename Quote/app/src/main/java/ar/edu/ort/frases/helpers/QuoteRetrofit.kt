package ar.edu.ort.frases.helpers

import ar.edu.ort.frases.model.Quote
import ar.edu.ort.frases.shared.IServiceQuotes
import ar.edu.ort.frases.shared.QuotesApi
import javax.inject.Inject


class QuoteRetrofit
    @Inject
    constructor(private val service:QuotesApi) : IServiceQuotes {

    override suspend fun getQuotes(): List<Quote>? {

        val response = service.getQuotes("")

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
