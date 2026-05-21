package ar.edu.ort.frases.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteQuoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class QuoteDatabase : RoomDatabase() {
    abstract fun favoriteQuoteDao(): FavoriteQuoteDao
}
