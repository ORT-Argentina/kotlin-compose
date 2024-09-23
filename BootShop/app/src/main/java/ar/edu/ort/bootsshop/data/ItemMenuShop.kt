package ar.edu.ort.bootsshop.data

import androidx.compose.runtime.Immutable

@Immutable
data class ItemMenuShop(
    val title: String,
    val image: String,
    val action: () -> Unit,
)