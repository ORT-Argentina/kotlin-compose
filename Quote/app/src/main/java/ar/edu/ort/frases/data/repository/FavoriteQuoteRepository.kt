package ar.edu.ort.frases.data.repository

import ar.edu.ort.frases.data.local.FavoriteQuoteDao
import ar.edu.ort.frases.data.local.FavoriteQuoteEntity
import ar.edu.ort.frases.model.Quote
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteQuoteRepository @Inject constructor(
    private val dao: FavoriteQuoteDao
) {
    fun getAllFavorites(): Flow<List<FavoriteQuoteEntity>> = dao.getAllFavorites()

    fun exists(quote: Quote): Flow<Boolean> = dao.exists(FavoriteQuoteEntity.fromQuote(quote).id)

    suspend fun addFavorite(quote: Quote) {
        dao.insertFavorite(FavoriteQuoteEntity.fromQuote(quote))
    }

    suspend fun removeFavorite(quote: Quote) {
        dao.deleteFavorite(FavoriteQuoteEntity.fromQuote(quote))
    }

    suspend fun removeFavoriteById(id: String) {
        dao.deleteFavoriteById(id)
    }
}
