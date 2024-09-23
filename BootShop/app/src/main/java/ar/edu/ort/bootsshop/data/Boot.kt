package ar.edu.ort.bootsshop.data

import androidx.compose.runtime.Immutable

@Immutable
data class Boot(
    val id: Int,
    val image: String,
    val title: String,
    val price: Double,
    val content: String,
    val sizes: Sizes,
    val stock: Int = 0
)