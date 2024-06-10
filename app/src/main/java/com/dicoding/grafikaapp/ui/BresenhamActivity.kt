package com.dicoding.grafikaapp.ui

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.grafikaapp.R
import com.dicoding.grafikaapp.databinding.ActivityBresenhamBinding
import com.dicoding.grafikaapp.databinding.ActivityDdaBinding

class BresenhamActivity : AppCompatActivity() {
    private val binding: ActivityBresenhamBinding by lazy {
        ActivityBresenhamBinding.inflate(layoutInflater)
    }
    private var isPencilIconClicked = false
    private var isPaletteIconClicked = false

    companion object {
        var path = Path()
        var paintBrush = Paint()
        var colorList = ArrayList<Int>()
        var currentBrush = Color.BLACK
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        supportActionBar?.hide()

        startDraw()
    }

    private fun startDraw(){
        binding.apply {
            btnBresenham.setOnClickListener {
                // Untuk mengganti dari false menjadi true
                isPencilIconClicked = !isPencilIconClicked

                if (isPencilIconClicked) { // ini untuk mengecek apakah isPencilIconClicked sudah true valuenya
                    btnBresenham.setImageResource(R.drawable.ic_selected_pencil)
                    btnBresenham.setBackgroundResource(R.drawable.background_cards)

                    btnPallete.setImageResource(R.drawable.ic_unselected_palette)
                    btnPallete.setBackgroundResource(R.drawable.background_card)

                    drawBresenham.visibility = View.VISIBLE
                } else {
                    btnBresenham.setImageResource(R.drawable.ic_unselected_pencil)
                    btnBresenham.setBackgroundResource(R.drawable.background_card)
                }
            }

            btnPallete.setOnClickListener {
                isPaletteIconClicked = !isPaletteIconClicked

                if (isPaletteIconClicked) {
                    colorPalate.visibility = View.VISIBLE

                    btnPallete.setImageResource(R.drawable.ic_selected_palette)
                    btnPallete.setBackgroundResource(R.drawable.background_cards)

                    btnBresenham.setImageResource(R.drawable.ic_unselected_pencil)
                    btnBresenham.setBackgroundResource(R.drawable.background_card)
                } else {
                    btnPallete.setImageResource(R.drawable.ic_unselected_palette)
                    btnPallete.setBackgroundResource(R.drawable.background_card)
                    colorPalate.visibility = View.INVISIBLE
                }
            }

            binding.btnClearAll.setOnClickListener {
                clearCanvas()
            }

            btnBlue.setOnClickListener {
                DdaActivity.paintBrush.color = resources.getColor(R.color.google_blue)
                currentColor(DdaActivity.paintBrush.color)
                colorPalate.visibility = View.INVISIBLE
                btnPallete.setImageResource(R.drawable.ic_unselected_palette)
                btnPallete.setBackgroundResource(R.drawable.background_card)
            }

            btnRed.setOnClickListener {
                DdaActivity.paintBrush.color = resources.getColor(R.color.google_red)
                currentColor(DdaActivity.paintBrush.color)
                colorPalate.visibility = View.INVISIBLE
                btnPallete.setImageResource(R.drawable.ic_unselected_palette)
                btnPallete.setBackgroundResource(R.drawable.background_card)
            }

            btnYellow.setOnClickListener {
                DdaActivity.paintBrush.color = resources.getColor(R.color.google_yellow)
                currentColor(DdaActivity.paintBrush.color)
                colorPalate.visibility = View.INVISIBLE
                btnPallete.setImageResource(R.drawable.ic_unselected_palette)
                btnPallete.setBackgroundResource(R.drawable.background_card)
            }

            btnGreen.setOnClickListener {
                DdaActivity.paintBrush.color = resources.getColor(R.color.google_green)
                currentColor(DdaActivity.paintBrush.color)
                colorPalate.visibility = View.INVISIBLE
                btnPallete.setImageResource(R.drawable.ic_unselected_palette)
                btnPallete.setBackgroundResource(R.drawable.background_card)
            }

            btnBlack.setOnClickListener {
                DdaActivity.paintBrush.color = Color.BLACK
                currentColor(DdaActivity.paintBrush.color)
                colorPalate.visibility = View.INVISIBLE
                btnPallete.setImageResource(R.drawable.ic_unselected_palette)
                btnPallete.setBackgroundResource(R.drawable.background_card)
            }
        }
    }

    private fun clearCanvas() {
        DdaActivity.path.reset()
        DdaActivity.colorList.clear()
        binding.drawBresenham.clear()
    }

    private fun currentColor(color: Int) {
        DdaActivity.currentBrush = color
        DdaActivity.path = Path()
    }
}