package com.teahousesoftware.oreb.views

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import org.jetbrains.anko.AnkoLogger

class FretboardGuidanceView : View, AnkoLogger {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    // ----- Drawing

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.save()
        // TODO: draw here
        canvas.restore()
    }
}