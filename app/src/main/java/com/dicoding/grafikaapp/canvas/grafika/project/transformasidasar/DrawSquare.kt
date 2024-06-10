package com.dicoding.grafikaapp.canvas.grafika.project.transformasidasar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.dicoding.grafikaapp.dataclass.grafikaproject.Square
import com.dicoding.grafikaapp.ui.SquareTransformasiActivity.Companion.colorList
import com.dicoding.grafikaapp.ui.SquareTransformasiActivity.Companion.currentBrush

class DrawSquare @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val square = mutableListOf<Square>()

    private val paintBrush = Paint().apply {
        color = currentBrush
        strokeWidth = 10f
        isAntiAlias = true
    }

    private var rotationAngle = 0f // Default rotation angle
    private var scaleFactor = 1f // Default scale factor
    private var translationX = 0f // Default translation in X direction
    private var translationY = 0f // Default translation in Y direction

    fun clear() {
        square.clear()
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                colorList.add(currentBrush)
                square.add(Square(event.x, event.y, event.x, event.y, currentBrush))
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                val current = square[square.size - 1]
                current.stopX = event.x
                current.stopY = event.y
                invalidate()
                return true
            }

            MotionEvent.ACTION_UP -> {
                val current = square[square.size - 1]
                current.stopX = event.x
                current.stopY = event.y
                invalidate()
                return true
            }

            else -> {
                return false
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        for (r in square) {
            val matrix = Matrix()

            // Apply translation
            matrix.postTranslate(translationX, translationY)

            // Apply rotation
            matrix.postRotate(rotationAngle, r.startX, r.startY) // Rotate around the starting point of the rectangle

            // Apply scale
            matrix.postScale(scaleFactor, scaleFactor, r.startX, r.startY) // Scale relative to the starting point of the rectangle

            val rotatedPoints = floatArrayOf(r.startX, r.startY, r.stopX, r.startY, r.stopX, r.stopY, r.startX, r.stopY)
            matrix.mapPoints(rotatedPoints) // Apply transformations to rectangle points

            paintBrush.color = r.color
            canvas.drawRect(
                rotatedPoints[0], rotatedPoints[1], // Top left
                rotatedPoints[4], rotatedPoints[5], // Bottom right
                paintBrush
            )
        }
    }

    fun rotateRight() {
        rotationAngle += 10f // Rotate by 10 degrees to the right
        invalidate()
    }

    fun rotateLeft() {
        rotationAngle -= 10f // Rotate by 10 degrees to the left
        invalidate()
    }

    fun resetRotation() {
        rotationAngle = 0f
        invalidate()
    }

    fun scaleUp() {
        scaleFactor += 0.1f // Increase scale factor by 0.1
        invalidate()
    }

    fun scaleDown() {
        scaleFactor -= 0.1f // Decrease scale factor by 0.1
        invalidate()
    }

    fun translateRight() {
        translationX += 10f // Translate 10 units to the right
        invalidate()
    }

    fun translateLeft() {
        translationX -= 10f // Translate 10 units to the left
        invalidate()
    }

    fun translateUp() {
        translationY -= 10f // Translate 10 units up
        invalidate()
    }

    fun translateDown() {
        translationY += 10f // Translate 10 units down
        invalidate()
    }
}