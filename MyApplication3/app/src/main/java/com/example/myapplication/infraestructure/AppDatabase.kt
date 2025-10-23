package com.example.myapplication.infraestructure

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.dao.MessageDao
import com.example.myapplication.domain.model.Message

@Database(entities = [Message::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
}