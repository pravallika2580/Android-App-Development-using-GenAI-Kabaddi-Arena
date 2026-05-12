package com.example.kabaddiarena

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlin.concurrent.thread

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val recyclerHistory = findViewById<RecyclerView>(R.id.recyclerHistory)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "kabaddi-db"
        )
            .fallbackToDestructiveMigration()
            .build()

        recyclerHistory.layoutManager = LinearLayoutManager(this)

        thread {
            val matches = db.matchDao().getAllMatches()

            runOnUiThread {
                recyclerHistory.adapter = HistoryAdapter(matches)
            }
        }
    }
}