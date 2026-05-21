package ar.edu.ort.frases.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteQuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(quote: FavoriteQuoteEntity)

    @Delete
    suspend fun deleteFavorite(quote: FavoriteQuoteEntity)

    @Query("DELETE FROM favorite_quotes WHERE id = :id")
    suspend fun deleteFavoriteById(id: String)

    @Query("SELECT * FROM favorite_quotes ORDER BY author ASC, text ASC")
    fun getAllFavorites(): Flow<List<FavoriteQuoteEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_quotes WHERE id = :id)")
    fun exists(id: String): Flow<Boolean>
}
