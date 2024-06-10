package com.dicoding.grafikaapp.ui

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.grafikaapp.R
import com.dicoding.grafikaapp.databinding.ActivityCirclePointBinding
import com.dicoding.grafikaapp.databinding.ActivitySquareTransformasiBinding

class SquareTransformasiActivity : AppCompatActivity() {
    private val binding: ActivitySquareTransformasiBinding by lazy {
        ActivitySquareTransformasiBinding.inflate(layoutInflater)
    }
    private var isPencilIconClicked = false
    private var isPaletteIconClicked = false

    companion object {
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

    private fun startDraw() {
        binding.apply {
            btnSquare.setOnClickListener {
                isPencilIconClicked = !isPencilIconClicked

                if (isPencilIconClicked) {
                    btnSquare.setImageResource(R.drawable.ic_selected_pencil)
                    btnSquare.setBackgroundResource(R.drawable.background_cards)
                    btnPallete.setImageResource(R.drawable.ic_unselected_palette)
                    btnPallete.setBackgroundResource(R.drawable.background_card)
                    drawSquare.visibility = View.VISIBLE
                } else {
                    btnSquare.setImageResource(R.drawable.ic_unselected_pencil)
                    btnSquare.setBackgroundResource(R.drawable.background_card)
                }
            }

            btnPallete.setOnClickListener {
                isPaletteIconClicked = !isPaletteIconClicked

                if (isPaletteIconClicked) {
                    colorPalate.visibility = View.VISIBLE
                    btnPallete.setImageResource(R.drawable.ic_selected_palette)
                    btnPallete.setBackgroundResource(R.drawable.background_cards)
                    btnSquare.setImageResource(R.drawable.ic_unselected_pencil)
                    btnSquare.setBackgroundResource(R.drawable.background_card)
                } else {
                    btnPallete.setImageResource(R.drawable.ic_unselected_palette)
                    btnPallete.setBackgroundResource(R.drawable.background_card)
                    colorPalate.visibility = View.INVISIBLE
                }
            }

            btnClearAll.setOnClickListener {
                clearCanvas()
            }

            btnBlue.setOnClickListener {
                paintBrush.color = resources.getColor(R.color.google_blue)
                currentColor(paintBrush.color)
                colorPalate.visibility = View.INVISIBLE
                btnPallete.setImageResource(R.drawable.ic_unselected_palette)
                btnPallete.setBackgroundResource(R.drawable.background_card)
            }

            btnRed.setOnClickListener {
                paintBrush.color = resources.getColor(R.color.google_red)
                currentColor(paintBrush.color)
                colorPalate.visibility = View.INVISIBLE
                btnPallete.setImageResource(R.drawable.ic_unselected_palette)
                btnPallete.setBackgroundResource(R.drawable.background_card)
            }

            btnYellow.setOnClickListener {
                paintBrush.color = resources.getColor(R.color.google_yellow)
                currentColor(paintBrush.color)
                colorPalate.visibility = View.INVISIBLE
                btnPallete.setImageResource(R.drawable.ic_unselected_palette)
                btnPallete.setBackgroundResource(R.drawable.background_card)
            }

            btnGreen.setOnClickListener {
                paintBrush.color = resources.getColor(R.color.google_green)
                currentColor(paintBrush.color)
                colorPalate.visibility = View.INVISIBLE
                btnPallete.setImageResource(R.drawable.ic_unselected_palette)
                btnPallete.setBackgroundResource(R.drawable.background_card)
            }

            btnBlack.setOnClickListener {
                paintBrush.color = Color.BLACK
                currentColor(paintBrush.color)
                colorPalate.visibility = View.INVISIBLE
                btnPallete.setImageResource(R.drawable.ic_unselected_palette)
                btnPallete.setBackgroundResource(R.drawable.background_card)
            }

            btnRight.setOnClickListener {
                drawSquare.rotateRight()
            }

            btnLeft.setOnClickListener {
                drawSquare.rotateLeft()
            }

            resetRotation.setOnClickListener {
                drawSquare.resetRotation()
            }

            btnResetRotation.setOnClickListener {
                drawSquare.resetRotation()
            }

            btnScaleUp.setOnClickListener {
                drawSquare.scaleUp()
            }

            btnScaleDown.setOnClickListener {
                drawSquare.scaleDown()
            }

            btnTranslateRight.setOnClickListener {
                drawSquare.translateRight()
            }

            btnTranslateLeft.setOnClickListener {
                drawSquare.translateLeft()
            }

            btnTranslateUp.setOnClickListener {
                drawSquare.translateUp()
            }

            btnTranslateDown.setOnClickListener {
                drawSquare.translateDown()
            }
        }
    }

    private fun clearCanvas() {
        colorList.clear()
        binding.drawSquare.clear()
    }

    private fun currentColor(color: Int) {
        currentBrush = color
    }

}