package com.example.kabaddiarena

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matches")
data class MatchEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val opponent: String,
    val date: String,

    val points: Int,

    // 🔥 NEW FIELDS (important for analytics)
    val raids: Int,
    val success: Int,
    val failed: Int,
    val tackles: Int
)