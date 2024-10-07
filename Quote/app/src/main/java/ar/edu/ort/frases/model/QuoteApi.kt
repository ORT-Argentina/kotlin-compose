package ar.edu.ort.frases.model

import com.google.gson.annotations.SerializedName

data class QuoteApi(
    @SerializedName("quote") val quote: String,
    @SerializedName("author") val author: String
)