package ar.edu.ort.frases.di

import android.content.Context
import androidx.room.Room
import ar.edu.ort.frases.data.local.FavoriteQuoteDao
import ar.edu.ort.frases.data.local.QuoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideQuoteDatabase(@ApplicationContext context: Context): QuoteDatabase =
        Room.databaseBuilder(
            context,
            QuoteDatabase::class.java,
            "quotes.db"
        ).build()

    @Provides
    fun provideFavoriteQuoteDao(database: QuoteDatabase): FavoriteQuoteDao =
        database.favoriteQuoteDao()
}
