package com.dicoding.grafikaapp.canvas.paint.normal

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.dicoding.grafikaapp.ui.PaintActivity.Companion.colorList
import com.dicoding.grafikaapp.ui.PaintActivity.Companion.currentBrush
import com.dicoding.grafikaapp.dataclass.paintnormal.Line

class DrawLine @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        var line = mutableListOf<Line>()
    }

    private val paintBrush = Paint().apply {
        color = currentBrush
        strokeWidth = 10f
        isAntiAlias = true
    }

    fun clear() {
        line.clear()
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                colorList.add(currentBrush)
                line.add(Line(event.x, event.y, event.x, event.y, currentBrush))
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                val current = line[line.size - 1]
                current.stopX = event.x
                current.stopY = event.y
                invalidate()
                return true
            }

            MotionEvent.ACTION_UP -> {
                val current = line[line.size - 1]
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
        for (l in line) {
            paintBrush.color = l.color
            canvas.drawLine(l.startX, l.startY, l.stopX, l.stopY, paintBrush)
        }
    }
}