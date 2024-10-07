package ar.edu.ort.frases.shared

import ar.edu.ort.frases.model.Quote

interface IServiceQuotes {
    suspend fun getQuotes(): List<Quote>?
}