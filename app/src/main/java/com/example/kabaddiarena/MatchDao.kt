package com.example.kabaddiarena

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MatchDao {

    @Insert
    fun insertMatch(match: MatchEntity)

    @Query("SELECT * FROM matches")
    fun getAllMatches(): List<MatchEntity>

    // 🔥 NEW: Get latest match (used in Performance screen)
    @Query("SELECT * FROM matches ORDER BY id DESC LIMIT 1")
    fun getLatestMatch(): MatchEntity?
}