package ar.edu.ort.frases.shared

import ar.edu.ort.frases.helpers.QuoteRetrofit
import ar.edu.ort.frases.model.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetServiceQuotes @Inject constructor(
    private val quoteRetrofit: QuoteRetrofit
){
   suspend fun invoke(): List<Quote>? = withContext(Dispatchers.IO){
       quoteRetrofit.getQuotes()
   }
}