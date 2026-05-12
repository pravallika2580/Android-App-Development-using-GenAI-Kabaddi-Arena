package com.example.kabaddiarena

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MatchEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun matchDao(): MatchDao
}