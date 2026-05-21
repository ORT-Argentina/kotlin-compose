package ar.edu.ort.frases.data.repository

import ar.edu.ort.frases.model.Quote
import ar.edu.ort.frases.shared.GetServiceQuotes
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuoteRepository @Inject constructor(
    private val getServiceQuotes: GetServiceQuotes
) {
    suspend fun getRandomQuote(): Result<Quote> = runCatching {
        val quotes = getServiceQuotes.invoke().orEmpty()
        quotes.firstOrNull() ?: error("No se encontraron frases.")
    }
}
