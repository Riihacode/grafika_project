package com.dicoding.grafikaapp.canvas.paint.normal

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.dicoding.grafikaapp.ui.PaintActivity.Companion.currentBrush
import com.dicoding.grafikaapp.ui.PaintActivity.Companion.path
import com.dicoding.grafikaapp.dataclass.paintnormal.Pencil

class DrawPencil @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val TOUCH_TOLERANCE = 4f

    private var mX = 0f
    private var mY = 0f

    private val dataPencil = mutableListOf<Pencil>()
    private val colorList = mutableListOf<Int>()

    private val paintBrush = Paint().apply {
        isAntiAlias = true
        isDither = true
        color = currentBrush
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 16f
        alpha = 0xff
    }

    fun updateColor(newColor: Int) {
        paintBrush.color = newColor
    }

    fun clear() {
        dataPencil.clear()
        invalidate()
    }

    private fun touchStart(x: Float, y: Float) {
        val p = Pencil(currentBrush, path)
        dataPencil.add(p)
        colorList.add(currentBrush)
        path.moveTo(x, y)
        mX = x
        mY = y
    }

    private fun touchMove(x: Float, y: Float) {
        val dx = Math.abs(x - mX)
        val dy = Math.abs(y - mY)
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            path.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2)
            mX = x
            mY = y
        }
    }

    private fun touchUp() {
        path.lineTo(mX, mY)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                touchStart(x, y)
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                touchMove(x, y)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                touchUp()
                invalidate()
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        for (p in dataPencil) {
            paintBrush.color = p.color
            canvas.drawPath(p.path!!, paintBrush)
            invalidate()
        }
    }

    fun undo() {
        if (dataPencil.size != 0) {
            dataPencil.removeAt(dataPencil.size - 1)
            invalidate()
        }
    }

}