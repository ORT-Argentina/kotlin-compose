package ar.edu.ort.frases.model

import com.google.gson.annotations.SerializedName

data class Quote(
    val quote: String,
    val author: String,
    val category: String
)