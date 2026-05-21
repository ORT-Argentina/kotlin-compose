package ar.edu.ort.frases.data.local

import ar.edu.ort.frases.model.Quote
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class FavoriteQuoteEntityTest {

    @Test
    fun sameQuoteAndAuthorProduceSameId() {
        val first = FavoriteQuoteEntity.fromQuote(
            Quote(quote = "Stay hungry", author = "Unknown", category = "inspire")
        )
        val second = FavoriteQuoteEntity.fromQuote(
            Quote(quote = "Stay hungry", author = "Unknown", category = "life")
        )

        assertEquals(first.id, second.id)
    }

    @Test
    fun differentAuthorProducesDifferentId() {
        val first = FavoriteQuoteEntity.fromQuote(
            Quote(quote = "Stay hungry", author = "Unknown", category = "inspire")
        )
        val second = FavoriteQuoteEntity.fromQuote(
            Quote(quote = "Stay hungry", author = "Other", category = "inspire")
        )

        assertNotEquals(first.id, second.id)
    }
}
