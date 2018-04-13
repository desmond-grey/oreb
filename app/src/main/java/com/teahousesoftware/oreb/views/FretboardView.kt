package com.teahousesoftware.oreb.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.ScaleGestureDetector
import android.view.View
import com.teahousesoftware.oreb.model.guitar.*
import com.teahousesoftware.oreb.views.paint.styles.*
import android.view.MotionEvent
import org.jetbrains.anko.AnkoLogger
import kotlin.math.max
import kotlin.math.min

// TODO: guitar measurements, scaling and display density are too intertwined.  See Guitar Measurments in the README.
class FretboardView : View, AnkoLogger {
    private val DRAW_SCALE_MIN = 75f     // Reasonable initial value for Larrivee on Galaxy Tab S2, wide orientation
    private val DRAW_SCALE_MAX = 250f

    private val scaleGestureDetector: ScaleGestureDetector
    private val guitar: Guitar

    private var currentDrawScale: Float = DRAW_SCALE_MIN       // Driven by pinch/zoom scaling.  Start zoomed out.

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    init {
        guitar = buildLarrivee()
        scaleGestureDetector = ScaleGestureDetector(context, ScaleListener())
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        scaleGestureDetector.onTouchEvent(motionEvent)        // Let the ScaleGestureDetector inspect all events.
        return true
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {

            val scaleFactor = scaleGestureDetector.scaleFactor

            currentDrawScale = currentDrawScale * scaleFactor
            currentDrawScale = min(currentDrawScale, DRAW_SCALE_MAX)  // largest we can scale
            currentDrawScale = max(currentDrawScale, DRAW_SCALE_MIN)  // smallest we can scale

            invalidate()

            return true
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.save()

        drawFretboard(canvas, currentDrawScale, guitar.fretboard)
        drawSideFretMarkers(canvas, currentDrawScale, guitar.fretboard)
        drawNut(canvas, currentDrawScale, guitar.nut)
        drawSaddle(canvas, currentDrawScale, guitar.saddle)
        drawFrets(canvas, currentDrawScale, guitar.fretboard.frets)
        drawStrings(canvas, currentDrawScale, guitar.strings)

        canvas.restore()
    }

    private fun drawFretboard(canvas: Canvas, scale: Float, fretboard: Fretboard) {
        canvas.drawRect(0f, 0f, fretboard.length * scale, fretboard.heightAtNut * scale, ebonyFill())
        canvas.drawRect(0f, 0f, fretboard.length * scale, fretboard.heightAtNut * scale, blackStrokeOnePixel())
    }

    private fun drawSideFretMarkers(canvas: Canvas, scale: Float, fretboard: Fretboard) {
        val offsetFromBottomOfFretboard = .25f

        val fretsWithMarkers = ArrayList<Fret>()
        fretsWithMarkers.add(fretboard.frets[3])
        fretsWithMarkers.add(fretboard.frets[5])
        fretsWithMarkers.add(fretboard.frets[7])
        fretsWithMarkers.add(fretboard.frets[9])
        fretsWithMarkers.add(fretboard.frets[12])
        fretsWithMarkers.add(fretboard.frets[15])
        fretsWithMarkers.add(fretboard.frets[17])
        fretsWithMarkers.add(fretboard.frets[19])

        for (fret in fretsWithMarkers) {
            val xCenterOfFretArea = fret.distanceFromNut - fret.distanceFromPreviousFret / 2

            if (fret.fretNumber == 12) {
                canvas.drawCircle((xCenterOfFretArea - .0625f) * scale, (fretboard.heightAtSaddle + offsetFromBottomOfFretboard) * scale, 3f, blackFill())
                canvas.drawCircle((xCenterOfFretArea + .0625f) * scale, (fretboard.heightAtSaddle + offsetFromBottomOfFretboard) * scale, 3f, blackFill())
            } else {
                canvas.drawCircle(xCenterOfFretArea * scale, (fretboard.heightAtSaddle + offsetFromBottomOfFretboard) * scale, 3f, blackFill())
            }
        }
    }

    private fun drawNut(canvas: Canvas, scale: Float, nut: Nut) {
        canvas.drawRect(nut.xPosition * scale, 0f, nut.width * scale, nut.height * scale, whiteFill())
        canvas.drawRect(nut.xPosition * scale, 0f, nut.width * scale, nut.height * scale, blackStrokeOnePixel())
    }

    private fun drawSaddle(canvas: Canvas, scale: Float, saddle: Saddle) {
        canvas.drawRect(saddle.xPosition * scale, 0f, (saddle.xPosition + saddle.width) * scale, saddle.height * scale, whiteFill())
        canvas.drawRect(saddle.xPosition * scale, 0f, (saddle.xPosition + saddle.width) * scale, saddle.height * scale, blackStrokeOnePixel())
    }

    private fun drawFrets(canvas: Canvas, scale: Float, frets: List<Fret>) {
        for (fret in frets) {
            canvas.drawRect(fret.distanceFromNut * scale, 0f, (fret.distanceFromNut + fret.width) * scale, fret.height * scale, blackFill())
            //            canvas.drawLine(fret.distanceFromNut * scale, 0, fret.distanceFromNut * scale, fret.height * scale, blackStrokeOnePixel);
        }
    }

    private fun drawStrings(canvas: Canvas, scale: Float, strings: List<GuitarString>) {
        for (string in strings) {
            canvas.drawLine(0f, string.yPosition * scale, string.length * scale, string.yPosition * scale, blackStrokeOnePixel())
        }
    }
}