package ar.edu.ort.frases.shared

import ar.edu.ort.frases.helpers.QuoteRetrofit
import ar.edu.ort.frases.model.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetServiceQuotes(
    private val quoteRetrofit: QuoteRetrofit
) {
   suspend fun execute(): List<Quote>? = withContext(Dispatchers.IO){
       quoteRetrofit.getQuotes()
   }
}