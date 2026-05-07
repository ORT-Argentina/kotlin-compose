package com.manish.mytask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.manish.mytask.model.Note

@Database(entities = [Note::class], version = 4, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
