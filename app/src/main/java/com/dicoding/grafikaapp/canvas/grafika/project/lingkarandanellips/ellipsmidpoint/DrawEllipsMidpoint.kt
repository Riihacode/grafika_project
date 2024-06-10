package com.dicoding.grafikaapp.canvas.grafika.project.lingkarandanellips.ellipsmidpoint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.dicoding.grafikaapp.canvas.grafika.project.titikdangaris.dda.DrawDda
import com.dicoding.grafikaapp.dataclass.grafikaproject.EllipsMidpoint
import com.dicoding.grafikaapp.ui.EllipsMidpointActivity.Companion.currentBrush

class DrawEllipsMidpoint @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val ellipsMidpoint = mutableListOf<EllipsMidpoint>()

    private val paintBrush = Paint().apply {
        strokeWidth = 10f
        color = currentBrush
        isAntiAlias = true
    }

    fun updateColor(newColor: Int) {
        paintBrush.color = newColor
    }

    fun clear() {
        ellipsMidpoint.clear()
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                ellipsMidpoint.add(EllipsMidpoint(event.x, event.y, 0f, 0f, currentBrush))
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                val ellipse = ellipsMidpoint.lastOrNull()
                ellipse?.let {
                    val dx = event.x - it.centerX
                    val dy = event.y - it.centerY
                    it.radiusX = Math.abs(dx)
                    it.radiusY = Math.abs(dy)
                    invalidate()
                }
                return true
            }
            MotionEvent.ACTION_UP -> {
                val ellipse = ellipsMidpoint.lastOrNull()
                ellipse?.let {
                    val dx = event.x - it.centerX
                    val dy = event.y - it.centerY
                    it.radiusX = Math.abs(dx)
                    it.radiusY = Math.abs(dy)
                    invalidate()
                }
                return true
            }
            else -> return false
        }
    }

    override fun onDraw(canvas: Canvas) {
        for (ellipse in ellipsMidpoint) {
            paintBrush.color = ellipse.color
            drawEllipseMidpoint(canvas, ellipse.centerX, ellipse.centerY, ellipse.radiusX, ellipse.radiusY, paintBrush)
        }
    }

    fun undo() {
        if (ellipsMidpoint.isNotEmpty()) {
            ellipsMidpoint.removeAt(ellipsMidpoint.size - 1)
            invalidate()
        }
    }

    private fun drawEllipseMidpoint(canvas: Canvas, xs1: Float, ys1: Float, rx: Float, ry: Float, paint: Paint) {
        var x = 0
        var y = ry.toInt()
        var d1: Float
        var d2: Float
        var dx: Float
        var dy: Float

        d1 = (ry * ry) - (rx * rx * ry) + (0.25 * rx * rx).toFloat()
        dx = 2 * (ry * ry) * x.toFloat()
        dy = 2 * (rx * rx) * y.toFloat()

        // Region satu
        do {
            display(canvas, xs1, ys1, x, y, paint)
            if (d1 < 0) {
                x++
                dx += 2 * (ry * ry)
                d1 += dx + (ry * ry)
            } else {
                x++
                y--
                dx += 2 * (ry * ry)
                dy -= 2 * (rx * rx)
                d1 += dx - dy + (ry * ry)
            }
        } while (dx < dy)

        // Region dua
        d2 = (ry * ry) - (rx * rx) * ry + (0.25 * rx * rx).toFloat()
        do {
            display(canvas, xs1, ys1, x, y, paint)
            if (d2 > 0) {
                x = x
                y--
                dy -= 2 * (rx * rx)
                d2 -= dy + (rx * rx)
            } else {
                x++
                y--
                dy -= 2 * (rx * rx)
                dx += 2 * (ry * ry)
                d2 += dx - dy + (rx * rx)
            }
        } while (y > 0)
    }

    private fun display(canvas: Canvas, xs: Float, ys: Float, x: Int, y: Int, paint: Paint) {
        canvas.drawPoint(xs + x, ys + y, paint)
        canvas.drawPoint(xs - x, ys - y, paint)
        canvas.drawPoint(xs + x, ys - y, paint)
        canvas.drawPoint(xs - x, ys + y, paint)
    }
}