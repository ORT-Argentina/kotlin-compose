package com.rickAndMorty.shared.infraestructure.characters

import com.google.gson.annotations.SerializedName

data class Paginado(
    @SerializedName("count") val id: Int,
    @SerializedName("pages") val paginas: String,
    @SerializedName("next") val proxima_pagina: String?,
    @SerializedName("prev") val pagina_anterior: String?
)