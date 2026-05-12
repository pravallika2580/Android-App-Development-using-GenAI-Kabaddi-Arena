package com.example.kabaddiarena

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStartMatch = findViewById<Button>(R.id.btnStartMatch)
        val btnHistory = findViewById<Button>(R.id.btnHistory)
        val btnPerformance = findViewById<Button>(R.id.btnPerformance)

        btnStartMatch.setOnClickListener {
            startActivity(Intent(this, MatchActivity::class.java))
        }

        btnHistory.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        btnPerformance.setOnClickListener {
            startActivity(Intent(this, PerformanceActivity::class.java))
        }
    }
}