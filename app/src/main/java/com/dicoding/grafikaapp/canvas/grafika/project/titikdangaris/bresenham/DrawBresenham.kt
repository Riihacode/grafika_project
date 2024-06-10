package com.dicoding.grafikaapp.canvas.grafika.project.titikdangaris.bresenham

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.dicoding.grafikaapp.canvas.grafika.project.titikdangaris.dda.DrawDda
import com.dicoding.grafikaapp.dataclass.grafikaproject.BresenhamLine
import com.dicoding.grafikaapp.ui.BresenhamActivity.Companion.colorList
import com.dicoding.grafikaapp.ui.BresenhamActivity.Companion.currentBrush

class DrawBresenham @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        var bresenham = mutableListOf<BresenhamLine>()
    }

    private val paintBrush = Paint().apply {
        color = currentBrush
        strokeWidth = 10f
        isAntiAlias = true
    }


    fun updateColor(newColor: Int) {
        paintBrush.color = newColor
    }

    fun clear() {
        bresenham.clear()
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                colorList.add(currentBrush)
                bresenham.add(BresenhamLine(event.x, event.y, event.x, event.y, currentBrush))
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                val current = bresenham[bresenham.size - 1]
                current.stopX = event.x
                current.stopY = event.y
                invalidate()
                return true
            }

            MotionEvent.ACTION_UP -> {
                val current = bresenham[bresenham.size - 1]
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
        for (l in bresenham) {
            paintBrush.color = l.color
            drawBresenhamLine(canvas, l.startX, l.startY, l.stopX, l.stopY, paintBrush)
        }
    }

    private fun drawBresenhamLine(canvas: Canvas, x1: Float, y1: Float, x2: Float, y2: Float, paint: Paint) {
        var x1Int = x1.toInt()
        var y1Int = y1.toInt()
        val x2Int = x2.toInt()
        val y2Int = y2.toInt()

        val dx = Math.abs(x2Int - x1Int)
        val dy = Math.abs(y2Int - y1Int)
        val sx = if (x1Int < x2Int) 1 else -1
        val sy = if (y1Int < y2Int) 1 else -1
        var err = dx - dy

        while (true) {
            canvas.drawPoint(x1Int.toFloat(), y1Int.toFloat(), paint)

            if (x1Int == x2Int && y1Int == y2Int) break
            val e2 = 2 * err
            if (e2 > -dy) {
                err -= dy
                x1Int += sx
            }
            if (e2 < dx) {
                err += dx
                y1Int += sy
            }
        }
    }

    fun undo() {
        if (bresenham.size != 0) {
            bresenham.removeAt(bresenham.size - 1)
            invalidate()
        }
    }
}