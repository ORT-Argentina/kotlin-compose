package com.example.myapplication.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "msg")
data class Message(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") val nombre: String?,
    @ColumnInfo(name = "created_at") val fecha_hora: String?
)