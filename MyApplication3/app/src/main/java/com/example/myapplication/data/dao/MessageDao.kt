package com.example.myapplication.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.domain.model.Message

@Dao
interface MessageDao {
    @Query("SELECT * FROM msg")
    fun getAll(): List<Message>

    @Query("SELECT * FROM msg WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Message>

/*    @Query("SELECT * FROM msg WHERE name LIKE :first LIMIT 1")
    fun findByName(first: String, last: String): Message

    @Insert
    fun insertAll(vararg messages: Message)

    @Delete
    fun delete(messages: Message)*/
}