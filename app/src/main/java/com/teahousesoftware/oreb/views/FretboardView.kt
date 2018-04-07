package com.teahousesoftware.oreb.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.teahousesoftware.oreb.views.paint.styles.rootNoteFillColor

class FretboardView : View {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.save()
        canvas.drawRect(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat(), rootNoteFillColor())
        canvas.restore()
    }
}