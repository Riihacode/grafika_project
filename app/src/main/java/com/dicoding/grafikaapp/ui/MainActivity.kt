package com.dicoding.grafikaapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.grafikaapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttonPaint=binding.paint
        val buttonDda = binding.dda
        val buttonBresenham = binding.bresenham
        val buttonCirclePoint = binding.circlePoint
        val buttonCircleMidpoint = binding.circleMidpoint
        val buttonEllipsMidpoint = binding.ellipsMidpoint
        val buttonSquareTransformasi = binding.squareTransformasi

        buttonPaint.setOnClickListener {
            val intent = Intent(this@MainActivity, PaintActivity::class.java)
            startActivity(intent)
        }

        buttonDda.setOnClickListener {
            val intent = Intent(this@MainActivity, DdaActivity::class.java)
            startActivity(intent)
        }

        buttonBresenham.setOnClickListener {
            val intent = Intent(this@MainActivity, BresenhamActivity::class.java)
            startActivity(intent)
        }

        buttonCirclePoint.setOnClickListener {
            val intent = Intent(this@MainActivity, CirclePointActivity::class.java)
            startActivity(intent)
        }

        buttonCircleMidpoint.setOnClickListener {
            val intent = Intent(this@MainActivity, CircleMidPointActivity::class.java)
            startActivity(intent)
        }

        buttonEllipsMidpoint.setOnClickListener {
            val intent = Intent(this@MainActivity, EllipsMidpointActivity::class.java)
            startActivity(intent)
        }

        buttonSquareTransformasi.setOnClickListener {
            val intent = Intent(this@MainActivity, SquareTransformasiActivity::class.java)
            startActivity(intent)
        }
    }
}