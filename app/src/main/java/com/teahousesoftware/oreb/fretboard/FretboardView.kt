package com.teahousesoftware.oreb.fretboard

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.ScaleGestureDetector
import android.view.View
import com.teahousesoftware.oreb.shared.model.guitar.*
import android.view.MotionEvent
import com.teahousesoftware.oreb.OrebActivity
import com.teahousesoftware.oreb.OrebViewModel
import com.teahousesoftware.oreb.shared.*
import com.teahousesoftware.oreb.shared.model.music.TheoreticalNote
import com.teahousesoftware.oreb.shared.model.music.Scale
import org.jetbrains.anko.AnkoLogger
import kotlin.math.max
import kotlin.math.min

// TODO: guitar measurements, scaling and display density are too intertwined.  See Guitar Measurments in the README.
class FretboardView : View, AnkoLogger {
    private var orebViewModel: OrebViewModel

    // Reasonable initial values for Larrivee on Galaxy Tab S2, wide orientation
    private val DRAW_SCALE_MIN = 75f
    private val DRAW_SCALE_MAX = 250f

    private val scaleGestureDetector: ScaleGestureDetector
    private val dragGestureDetector: GestureDetector
    private var isScaling = false            // scale and drag see the same events.  isScaling boolean allows us to avoid dragging while scaling

    private var currentDrawScale: Float = DRAW_SCALE_MIN       // Driven by pinch/zoom scaling.  Start zoomed out.

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    init {
        orebViewModel = ViewModelProviders.of((context as OrebActivity)).get(OrebViewModel::class.java)
        scaleGestureDetector = ScaleGestureDetector(context, ScaleListener())
        dragGestureDetector = GestureDetector(context, DragListener())
    }

    // ----- Gesture handling

    @SuppressLint("ClickableViewAccessibility")     // TODO: support accessibility?
    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        scaleGestureDetector.onTouchEvent(motionEvent)
        dragGestureDetector.onTouchEvent(motionEvent)
        return true
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            isScaling = true
            return true
        }

        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            val scaleFactor = scaleGestureDetector.scaleFactor

            currentDrawScale = currentDrawScale * scaleFactor
            currentDrawScale = min(currentDrawScale, DRAW_SCALE_MAX)  // largest we can scale
            currentDrawScale = max(currentDrawScale, DRAW_SCALE_MIN)  // smallest we can scale

            invalidate()

            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector) {
            isScaling = false
        }

    }

    inner class DragListener : GestureDetector.SimpleOnGestureListener() {
        override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
            if (!isScaling) {
                // TODO: Implement Drag-to-Pan   https://developer.android.com/training/gestures/scale.html
            }
            return true
        }
    }

    // ----- Drawing

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // TODO: should I be accessing ViewModel from this custom view?
        val guitar = orebViewModel.guitar.value!!

        canvas.save()

        drawFretboard(canvas, currentDrawScale, guitar.fretboard)
        drawSideFretMarkers(canvas, currentDrawScale, guitar.fretboard)
        drawNut(canvas, currentDrawScale, guitar.nut)
        drawSaddle(canvas, currentDrawScale, guitar.saddle)
        drawFrets(canvas, currentDrawScale, guitar.fretboard.frets)
        drawStrings(canvas, currentDrawScale, guitar.strings)

        drawGuidanceNotes(
                canvas,
                currentDrawScale,
                guitar,
                orebViewModel.key.value!!,
                orebViewModel.scale.value!!
        )

        canvas.restore()
    }

    private fun drawFretboard(canvas: Canvas, scale: Float, fretboard: Fretboard) {
        canvas.drawRect(0f, 0f, fretboard.length * scale, fretboard.heightAtNut * scale, transparentEbonyFill())
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
                canvas.drawCircle((xCenterOfFretArea - .0625f) * scale, (fretboard.heightAtSaddle + offsetFromBottomOfFretboard) * scale, 4f, blackFill())
                canvas.drawCircle((xCenterOfFretArea + .0625f) * scale, (fretboard.heightAtSaddle + offsetFromBottomOfFretboard) * scale, 4f, blackFill())
            } else {
                canvas.drawCircle(xCenterOfFretArea * scale, (fretboard.heightAtSaddle + offsetFromBottomOfFretboard) * scale, 4f, blackFill())
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

    private fun drawGuidanceNotes(
            canvas: Canvas,
            drawScale: Float,
            guitar: Guitar,
            key: TheoreticalNote,
            scale: Scale) {
        val scaleNotes = scale.generateNotesForKey(key)
        val chromaticScaleNotes = orebViewModel.scales.find { it.name == "Chromatic Scale" }!!.generateNotesForKey(key)

        for (string in guitar.strings) {
            val yPosition = string.yPosition

            for (fret in guitar.fretboard.frets) {
                val frettedNote = guitar.noteTable.get(string, fret)
                if (scaleNotes.contains(frettedNote.theoreticalNote)) {
                    val xCenterOfFretArea = fret.distanceFromNut - fret.distanceFromPreviousFret / 2

                    var fillColor = whiteFill()    // default fill
                    val noteNumber = chromaticScaleNotes.indexOf(frettedNote.theoreticalNote)
                    when (noteNumber) {
                        0 -> fillColor = firstNoteFillColor()
                        1 -> fillColor = secondNoteFillColor()
                        2 -> fillColor = thirdNoteFillColor()
                        3 -> fillColor = fourthNoteFillColor()
                        4 -> fillColor = fifthNoteFillColor()
                        5 -> fillColor = sixthNoteFillColor()
                        6 -> fillColor = seventhNoteFillColor()
                        7 -> fillColor = eighthNoteFillColor()
                        8 -> fillColor = ninthNoteFillColor()
                        9 -> fillColor = tenthNoteFillColor()
                        10 -> fillColor = eleventhNoteFillColor()
                        11 -> fillColor = twelfthNoteFillColor()
                        else -> {
                            whiteFill()
                        }
                    }

                    canvas.drawCircle(xCenterOfFretArea * drawScale, yPosition * drawScale, 15f, fillColor)
                    canvas.drawCircle(xCenterOfFretArea * drawScale, yPosition * drawScale, 15f, blackStrokeOnePixel())
                }
            }
        }
    }
}