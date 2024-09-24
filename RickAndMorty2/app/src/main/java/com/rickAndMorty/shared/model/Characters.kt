package com.rickAndMorty.shared.model

interface Characters {
    suspend fun getAll(): List<Character>?
}
