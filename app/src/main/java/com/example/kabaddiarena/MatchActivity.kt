package com.example.kabaddiarena

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlin.concurrent.thread

class MatchActivity : AppCompatActivity() {

    private var points = 0
    private var totalRaids = 0
    private var successfulRaids = 0
    private var failedRaids = 0
    private var tackles = 0

    private var lastAction = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "kabaddi-db"
        ).fallbackToDestructiveMigration()
            .build()

        val txtPoints = findViewById<TextView>(R.id.txtPoints)
        val btnRaid = findViewById<Button>(R.id.btnRaid)
        val btnRaidFailed = findViewById<Button>(R.id.btnRaidFailed)
        val btnTackle = findViewById<Button>(R.id.btnTackle)
        val btnUndo = findViewById<Button>(R.id.btnUndo)
        val btnFinish = findViewById<Button>(R.id.btnFinish)
        val btnPerformance = findViewById<Button>(R.id.btnPerformance)
        val timerCircle = findViewById<FrameLayout>(R.id.timerCircle)
        val tvTimer = findViewById<TextView>(R.id.tvTimer)

        val etOpponent = findViewById<EditText>(R.id.etOpponent)
        val etDate = findViewById<EditText>(R.id.etDate)

        // RAID SUCCESS
        btnRaid.setOnClickListener {
            points += 1
            totalRaids++
            successfulRaids++
            lastAction = "raid"
            txtPoints.text = "Points: $points"
        }

        // RAID FAILED
        btnRaidFailed.setOnClickListener {
            points -= 1
            totalRaids++
            failedRaids++
            lastAction = "failed"
            txtPoints.text = "Points: $points"
        }

        // TACKLE
        btnTackle.setOnClickListener {
            points += 2
            tackles++
            lastAction = "tackle"
            txtPoints.text = "Points: $points"
        }

        // UNDO
        btnUndo.setOnClickListener {
            when (lastAction) {
                "raid" -> {
                    points--
                    totalRaids--
                    successfulRaids--
                }
                "failed" -> {
                    points++
                    totalRaids--
                    failedRaids--
                }
                "tackle" -> {
                    points -= 2
                    tackles--
                }
            }
            txtPoints.text = "Points: $points"
            lastAction = ""
        }

        // TIMER
        timerCircle.setOnClickListener {

            object : CountDownTimer(30000, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    tvTimer.text = "${millisUntilFinished / 1000}"
                }

                override fun onFinish() {
                    tvTimer.text = "30"

                    Toast.makeText(
                        this@MatchActivity,
                        "Raid Over!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }.start()
        }

        // FINISH MATCH (🔥 FIXED SAVE)
        btnFinish.setOnClickListener {

            val opponentName = etOpponent.text.toString()
            val matchDate = etDate.text.toString()

            thread {
                db.matchDao().insertMatch(
                    MatchEntity(
                        opponent = opponentName,
                        date = matchDate,
                        points = points,
                        raids = totalRaids,
                        success = successfulRaids,
                        failed = failedRaids,
                        tackles = tackles
                    )
                )
            }

            Toast.makeText(this, "Match Saved!", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, HistoryActivity::class.java))
        }

        // PERFORMANCE
        btnPerformance.setOnClickListener {
            startActivity(Intent(this, PerformanceActivity::class.java))
        }
    }
}