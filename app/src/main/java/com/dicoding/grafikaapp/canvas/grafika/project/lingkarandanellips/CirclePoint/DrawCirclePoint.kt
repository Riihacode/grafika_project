package com.dicoding.grafikaapp.canvas.grafika.project.lingkarandanellips.CirclePoint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.dicoding.grafikaapp.canvas.grafika.project.titikdangaris.bresenham.DrawBresenham
import com.dicoding.grafikaapp.dataclass.grafikaproject.CirclePoint
import com.dicoding.grafikaapp.ui.CirclePointActivity
import com.dicoding.grafikaapp.ui.CirclePointActivity.Companion.currentBrush
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class DrawCirclePoint @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val circlePoint = mutableListOf<CirclePoint>()

    private val paintBrush = Paint().apply {
        strokeWidth = 10f
        color = currentBrush
        isAntiAlias = true
    }

    fun clear() {
        circlePoint.clear()
        invalidate()
    }

    fun updateColor(newColor: Int) {
        paintBrush.color = newColor
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                circlePoint.add(CirclePoint(event.x, event.y, 0f, currentBrush))
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                val circle = circlePoint.lastOrNull()
                circle?.let {
                    val dx = event.x - it.centerX
                    val dy = event.y - it.centerY
                    it.radius = Math.sqrt((dx * dx + dy * dy).toDouble()).toFloat()
                    invalidate()
                }
                return true
            }
            MotionEvent.ACTION_UP -> {
                val circle = circlePoint.lastOrNull()
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
        for (circle in circlePoint) {
            paintBrush.color = circle.color
            canvas.drawCircle(circle.centerX, circle.centerY, circle.radius, paintBrush)
        }
    }

    fun undo() {
        if (circlePoint.isNotEmpty()) {
            circlePoint.removeAt(circlePoint.size - 1)
            invalidate()
        }
    }
}
