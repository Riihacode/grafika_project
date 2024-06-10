package com.dicoding.grafikaapp.canvas.grafika.project.lingkarandanellips.circlemidpoint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.dicoding.grafikaapp.canvas.grafika.project.titikdangaris.dda.DrawDda
import com.dicoding.grafikaapp.dataclass.grafikaproject.CircleMidpoint
import com.dicoding.grafikaapp.ui.CircleMidPointActivity.Companion.currentBrush

class DrawCircleMidpoint @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val circleMidpoint = mutableListOf<CircleMidpoint>()

    private val paintBrush = Paint().apply {
        strokeWidth = 10f
        color = currentBrush
        isAntiAlias = true
    }

    fun updateColor(newColor: Int) {
        paintBrush.color = newColor
    }

    fun clear() {
        circleMidpoint.clear()
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                circleMidpoint.add(CircleMidpoint(event.x, event.y, 0f, currentBrush))
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                val circle = circleMidpoint.lastOrNull()
                circle?.let {
                    val dx = event.x - it.centerX
                    val dy = event.y - it.centerY
                    it.radius = Math.sqrt((dx * dx + dy * dy).toDouble()).toFloat()
                    invalidate()
                }
                return true
            }
            MotionEvent.ACTION_UP -> {
                val circle = circleMidpoint.lastOrNull()
                circle?.let {
                    val dx = event.x - it.centerX
                    val dy = event.y - it.centerY
                    it.radius = Math.sqrt((dx * dx + dy * dy).toDouble()).toFloat()
                    invalidate()
                }
                return true
            }
            else -> return false
        }
    }

    override fun onDraw(canvas: Canvas) {
        for (circle in circleMidpoint) {
            paintBrush.color = circle.color
            drawCircleMidPoint(canvas, circle.centerX, circle.centerY, circle.radius, paintBrush)
        }
    }

    fun undo() {
        if (circleMidpoint.isNotEmpty()) {
            circleMidpoint.removeAt(circleMidpoint.size - 1)
            invalidate()
        }
    }

    private fun drawCircleMidPoint(canvas: Canvas, xCenter: Float, yCenter: Float, radius: Float, paint: Paint) {
        var x = 0
        var y = radius.toInt()
        var p = 1 - radius

        while (x < y) {
            x++
            if (p < 0) {
                p += 2 * x + 1
            } else {
                y--
                p += 2 * x - 2 * y + 1
            }
            plotCirclePoints(canvas, xCenter, yCenter, x, y, paint)
        }
    }

    private fun plotCirclePoints(canvas: Canvas, xCenter: Float, yCenter: Float, x: Int, y: Int, paint: Paint) {
        canvas.drawPoint(xCenter + x, yCenter + y, paint)
        canvas.drawPoint(xCenter - x, yCenter + y, paint)
        canvas.drawPoint(xCenter + x, yCenter - y, paint)
        canvas.drawPoint(xCenter - x, yCenter - y, paint)
        canvas.drawPoint(xCenter + y, yCenter + x, paint)
        canvas.drawPoint(xCenter - y, yCenter + x, paint)
        canvas.drawPoint(xCenter + y, yCenter - x, paint)
        canvas.drawPoint(xCenter - y, yCenter - x, paint)
    }
}