package ar.edu.ort.frases.shared

import ar.edu.ort.frases.model.Dog
import ar.edu.ort.frases.model.Quote

interface IServiceQuotes {
    suspend fun getQuotes(): List<Quote>?

    suspend fun getDogs(): List<Dog>?
}