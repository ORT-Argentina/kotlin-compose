package com.rickAndMorty.shared.infraestructure.characters

import com.google.gson.annotations.SerializedName
import com.rickAndMorty.shared.model.Character

data class CharactersResponse(
    @SerializedName("results")
    val results: List<CharacterDTO>
) {
    fun toModel() = results.map {
        Character(
            name = it.name,
            status = it.status,
            imageUrl = it.imageUrl,
        )
    }
}