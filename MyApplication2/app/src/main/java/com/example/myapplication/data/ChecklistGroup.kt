package com.example.myapplication.data

import androidx.compose.ui.graphics.Color

data class ChecklistGroup(
    val id: Int,
    val title: String,
    val color: Color,
    val completedTasks: Int,
    val totalTasks: Int
)