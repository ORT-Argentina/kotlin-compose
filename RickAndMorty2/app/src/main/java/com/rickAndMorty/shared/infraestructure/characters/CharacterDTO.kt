package com.rickAndMorty.shared.infraestructure.characters

import com.google.gson.annotations.SerializedName

data class CharacterDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("image") val imageUrl: String,
)
