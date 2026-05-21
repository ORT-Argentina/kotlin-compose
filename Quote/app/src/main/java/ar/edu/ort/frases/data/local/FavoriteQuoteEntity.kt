package ar.edu.ort.frases.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import ar.edu.ort.frases.model.Quote

@Entity(tableName = "favorite_quotes")
data class FavoriteQuoteEntity(
    @PrimaryKey val id: String,
    val text: String,
    val author: String,
    val category: String
) {
    fun toQuote(): Quote = Quote(
        quote = text,
        author = author,
        category = category
    )

    companion object {
        fun fromQuote(quote: Quote): FavoriteQuoteEntity {
            val normalizedText = quote.quote.trim().lowercase()
            val normalizedAuthor = quote.author.trim().lowercase()
            return FavoriteQuoteEntity(
                id = "$normalizedText|$normalizedAuthor",
                text = quote.quote,
                author = quote.author,
                category = quote.category
            )
        }
    }
}
