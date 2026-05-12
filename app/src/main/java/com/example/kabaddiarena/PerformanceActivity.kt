package com.example.kabaddiarena

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import kotlin.concurrent.thread
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

class PerformanceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_performance)

        val tvPerformance = findViewById<TextView>(R.id.tvPerformance)
        val pieChart = findViewById<PieChart>(R.id.pieChart)
        val lineChart = findViewById<LineChart>(R.id.lineChart)
        val btnShare = findViewById<Button>(R.id.btnShare)
        val btnHeatmap = findViewById<Button>(R.id.btnHeatmap)
        val performanceLayout = findViewById<View>(R.id.performanceLayout)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "kabaddi-db"
        ).fallbackToDestructiveMigration()
            .build()

        thread {

            val match = db.matchDao().getLatestMatch()

            runOnUiThread {

                if (match == null) {
                    tvPerformance.text = "No match data available"
                    return@runOnUiThread
                }

                val totalRaids = match.raids
                val successfulRaids = match.success
                val failedRaids = match.failed
                val tackles = match.tackles

                val successPercent =
                    if (totalRaids > 0) (successfulRaids * 100) / totalRaids else 0

                tvPerformance.text = """
                    Total Raids: $totalRaids
                    Successful Raids: $successfulRaids
                    Failed Raids: $failedRaids
                    Tackles: $tackles
                    Success %: $successPercent%
                """.trimIndent()

                // ================= PIE CHART =================
                val entries = ArrayList<PieEntry>()
                entries.add(PieEntry(successfulRaids.toFloat(), "Successful"))
                entries.add(PieEntry(failedRaids.toFloat(), "Failed"))
                entries.add(PieEntry(tackles.toFloat(), "Tackles"))

                val dataSet = PieDataSet(entries, "Match Breakdown")
                dataSet.setColors(Color.GREEN, Color.RED, Color.BLUE)
                dataSet.valueTextColor = Color.WHITE

                val data = PieData(dataSet)

                pieChart.data = data
                pieChart.description.isEnabled = false
                pieChart.centerText = "Stats"
                pieChart.setCenterTextColor(Color.WHITE)
                pieChart.legend.isEnabled = true
                pieChart.legend.textColor = Color.WHITE
                pieChart.setDrawEntryLabels(false)
                pieChart.animateY(1000)
                pieChart.invalidate()

                // ================= LINE CHART =================
                val lineEntries = ArrayList<Entry>()
                lineEntries.add(Entry(0f, successfulRaids.toFloat()))
                lineEntries.add(Entry(1f, failedRaids.toFloat()))
                lineEntries.add(Entry(2f, tackles.toFloat()))

                val lineDataSet = LineDataSet(lineEntries, "Match Stats")
                lineDataSet.valueTextColor = Color.WHITE
                lineDataSet.color = Color.CYAN
                lineDataSet.setCircleColor(Color.WHITE)

                val lineData = LineData(lineDataSet)

                lineChart.data = lineData
                lineChart.description.isEnabled = false
                lineChart.legend.isEnabled = true
                lineChart.legend.textColor = Color.WHITE
                lineChart.xAxis.textColor = Color.WHITE
                lineChart.axisLeft.textColor = Color.WHITE
                lineChart.axisRight.textColor = Color.WHITE
                lineChart.invalidate()

                // ================= SHARE =================
                btnShare.setOnClickListener {

                    performanceLayout.post {

                        val bitmap = Bitmap.createBitmap(
                            performanceLayout.width,
                            performanceLayout.height,
                            Bitmap.Config.ARGB_8888
                        )

                        val canvas = android.graphics.Canvas(bitmap)
                        performanceLayout.draw(canvas)

                        try {

                            val file = File(cacheDir, "performance_card.png")

                            val outputStream = FileOutputStream(file)

                            bitmap.compress(
                                Bitmap.CompressFormat.PNG,
                                100,
                                outputStream
                            )

                            outputStream.flush()
                            outputStream.close()

                            val uri = FileProvider.getUriForFile(
                                this,
                                "${packageName}.provider",
                                file
                            )

                            val shareIntent = Intent(Intent.ACTION_SEND)

                            shareIntent.type = "image/png"

                            shareIntent.putExtra(Intent.EXTRA_STREAM, uri)

                            shareIntent.addFlags(
                                Intent.FLAG_GRANT_READ_URI_PERMISSION
                            )

                            startActivity(
                                Intent.createChooser(
                                    shareIntent,
                                    "Share Performance"
                                )
                            )

                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }

                // ================= HEATMAP =================
                btnHeatmap.setOnClickListener {
                    val intent = Intent(this@PerformanceActivity, HeatmapActivity::class.java)
                    intent.putExtra("redX", (50..220).random())
                    intent.putExtra("redY", (80..400).random())
                    intent.putExtra("greenX", (50..220).random())
                    intent.putExtra("greenY", (80..400).random())
                    startActivity(intent)
                }
            }
        }
    }
}