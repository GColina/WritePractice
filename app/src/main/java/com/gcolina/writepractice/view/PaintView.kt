package com.gcolina.writepractice.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.gcolina.writepractice.R
import com.gcolina.writepractice.view.MainActivity.Companion.paintBrush
import com.gcolina.writepractice.view.MainActivity.Companion.path


class PaintView : View {

    /*Este params/Parametros es el responsable de saber cual es la altura y el ancho de nuestro lienzo con respecto al diseño principal. */
    var params: ViewGroup.LayoutParams? = null

    /*  companion object { No utilizar mas }*/
    var pathList = ArrayList<Path>()
    var colorList = ArrayList<Int>()
    var currentBrush = resources.getColor(R.color.black)


    constructor(context: Context) : this(context, null) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        init()
    }

    private fun init() {/* Esto es para suavizar la textura de nuestros trazos.*/
        paintBrush.isAntiAlias = true/*Brush = pincel y currentbrush es la pintura que escogiste*/
        paintBrush.color = currentBrush
        paintBrush.style = Paint.Style.STROKE/*La union de trazos que se van generando*/
        paintBrush.strokeJoin =
            Paint.Join.ROUND/*Brush Size = Pincel tamaño  / Como su nombre indica este es el tamaño del pincel.*//*paintBrush.strokeWidth = 40f*/
        params = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var x = event.x
        var y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x, y)
                pathList.add(path)
                colorList.add(currentBrush)
            }

            else -> return false
        }
        postInvalidate()
        return false;
    }

    override fun onDraw(canvas: Canvas) {

        for (i in pathList.indices) {
            paintBrush.setColor(colorList[i])
            canvas.drawPath(pathList[i], paintBrush)
            invalidate()
        }
    }
}