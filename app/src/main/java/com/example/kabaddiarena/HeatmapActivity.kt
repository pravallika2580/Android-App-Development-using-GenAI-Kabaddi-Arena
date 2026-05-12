package com.example.kabaddiarena

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class HeatmapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heatmap)

        val redMarker = findViewById<View>(R.id.redMarker)
        val greenMarker = findViewById<View>(R.id.greenMarker)

        val redX = intent.getIntExtra("redX", 70)
        val redY = intent.getIntExtra("redY", 120)

        val greenX = intent.getIntExtra("greenX", 220)
        val greenY = intent.getIntExtra("greenY", 320)

        val redParams = redMarker.layoutParams as FrameLayout.LayoutParams
        redParams.leftMargin = redX
        redParams.topMargin = redY
        redMarker.layoutParams = redParams

        val greenParams = greenMarker.layoutParams as FrameLayout.LayoutParams
        greenParams.leftMargin = greenX
        greenParams.topMargin = greenY
        greenMarker.layoutParams = greenParams
    }
}