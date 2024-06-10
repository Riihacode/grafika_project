package com.dicoding.grafikaapp.canvas.grafika.project.titikdangaris.dda

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.dicoding.grafikaapp.dataclass.grafikaproject.Dda
import com.dicoding.grafikaapp.ui.DdaActivity.Companion.colorList
import com.dicoding.grafikaapp.ui.DdaActivity.Companion.currentBrush

class DrawDda @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        var dda = mutableListOf<Dda>()
    }

    private val paintBrush = Paint().apply {
        color = currentBrush
        strokeWidth = 10f
        isAntiAlias = true
    }

    fun clear() {
        dda.clear()
        invalidate()
    }

    fun updateColor(newColor: Int) {
        paintBrush.color = newColor
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                colorList.add(currentBrush)
                dda.add(Dda(event.x, event.y, event.x, event.y, currentBrush))
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                val current = dda[dda.size - 1]
                current.stopX = event.x
                current.stopY = event.y
                invalidate()
                return true
            }

            MotionEvent.ACTION_UP -> {
                val current = dda[dda.size - 1]
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
        for (l in dda) {
            paintBrush.color = l.color
            drawDDALine(canvas, l.startX, l.startY, l.stopX, l.stopY, paintBrush)
        }
    }

    private fun drawDDALine(canvas: Canvas, x1: Float, y1: Float, x2: Float, y2: Float, paint: Paint) {
        val dx = x2 - x1
        val dy = y2 - y1
        val steps = maxOf(Math.abs(dx), Math.abs(dy))

        val xIncrement = dx / steps
        val yIncrement = dy / steps

        var x = x1
        var y = y1

        for (i in 0..steps.toInt()) {
            canvas.drawPoint(x, y, paint)
            x += xIncrement
            y += yIncrement
        }
    }

    fun undo() {
        if (dda.size != 0) {
            dda.removeAt(dda.size - 1)
            invalidate()
        }
    }
}