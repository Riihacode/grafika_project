package com.dicoding.grafikaapp.canvas.paint.normal

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.dicoding.grafikaapp.ui.PaintActivity.Companion.colorList
import com.dicoding.grafikaapp.ui.PaintActivity.Companion.currentBrush
import com.dicoding.grafikaapp.dataclass.paintnormal.Rectangle

class DrawRectangle @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val dataRectangle = mutableListOf<Rectangle>()

    private val paintBrush = Paint().apply {
        color = currentBrush
        strokeWidth = 10f
        isAntiAlias = true
    }

    fun clear() {
        dataRectangle.clear()
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                colorList.add(currentBrush)
                dataRectangle.add(Rectangle(event.x, event.y, event.x, event.y, currentBrush))
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                val current = dataRectangle[dataRectangle.size - 1]
                current.stopX = event.x
                current.stopY = event.y
                invalidate()
                return true
            }

            MotionEvent.ACTION_UP -> {
                val current = dataRectangle[dataRectangle.size - 1]
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
        for (r in dataRectangle) {
            paintBrush.color = r.color
            canvas.drawRect(r.startX, r.startY, r.stopX, r.stopY, paintBrush)
        }
    }
}