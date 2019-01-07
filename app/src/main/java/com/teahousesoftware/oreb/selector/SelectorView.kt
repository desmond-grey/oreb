package com.teahousesoftware.oreb.selector

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.teahousesoftware.oreb.OrebActivity
import com.teahousesoftware.oreb.OrebViewModel
import com.teahousesoftware.oreb.shared.*
import com.teahousesoftware.oreb.shared.canvas.CanvasOriginTranslator
import com.teahousesoftware.oreb.shared.model.music.Key
import com.teahousesoftware.oreb.shared.model.music.TheoreticalNote
import org.jetbrains.anko.AnkoLogger

class SelectorView : View, AnkoLogger {
    private var orebViewModel: OrebViewModel

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    init {
        orebViewModel = ViewModelProviders.of((context as OrebActivity)).get(OrebViewModel::class.java)
    }


    // ----- Drawing

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val tonic = orebViewModel.currentTonic.value!!
        val diatonicScale = orebViewModel.currentScale.value!!
        val diatonicKey = Key(tonic, diatonicScale)

        val chromaticScale = orebViewModel.scales.find { it.name == "Chromatic" }!!
        val chromaticKey = Key(diatonicKey.tonic, chromaticScale)   // todo: this should probably just be in the viewModel

        val canvasOriginTranslator = CanvasOriginTranslator(width, height)      // todo: init this earlier in the lifecycle

        canvas.save()

        drawNoteSections(canvas, canvasOriginTranslator, chromaticKey)

        drawNoteLabels(canvas, canvasOriginTranslator, chromaticKey, diatonicKey)
        drawNoteNumbers(canvas, canvasOriginTranslator, chromaticKey, diatonicKey)

        drawNoteSpiralBackground(canvas, canvasOriginTranslator, chromaticKey)
        drawNoteSpiralNotes(canvas, canvasOriginTranslator, chromaticKey, diatonicKey)

        canvas.restore()
    }

    private fun drawNoteSections(canvas: Canvas, canvasOriginTranslator: CanvasOriginTranslator, chromaticKey: Key) {
        val centerX = width / 2
        val centerY = height / 2
        val sectionRadius = Math.min(centerX, centerY)      // 1/2 the shortest dimension

        for (guitarNote in orebViewModel.guitar.getNoteRange()) {    // ordered from lowest to highest
            val noteNumberInChromaticScale = chromaticKey.noteNumberInKey(guitarNote.theoreticalNote)
            val middleAngle = angleForChromaticKeyNoteNumber(noteNumberInChromaticScale)
            val leftAngle = subtractDegree(middleAngle, 15)
            val rightAngle = addDegree(middleAngle, 15)

            val (leftX, leftY) = canvasOriginTranslator.translate(calc2DCoordinate(centerX, centerY, leftAngle, sectionRadius))
            val (rightX, rightY) = canvasOriginTranslator.translate(calc2DCoordinate(centerX, centerY, rightAngle, sectionRadius))
            canvas.drawLine(centerX.toFloat(), centerY.toFloat(), leftX.toFloat(), leftY.toFloat(), blackStrokeOnePixel())
            canvas.drawLine(centerX.toFloat(), centerY.toFloat(), rightX.toFloat(), rightY.toFloat(), blackStrokeOnePixel())
        }
    }


    private fun drawNoteLabels(canvas: Canvas, canvasOriginTranslator: CanvasOriginTranslator, chromaticKey: Key, key: Key) {
        val centerX = width / 2
        val centerY = height / 2
        val sectionRadius = Math.min(centerX, centerY)      // 1/2 the shortest dimension

        for (theoreticalNote in key.notes) {
            val noteNumberInChromaticScale = chromaticKey.noteNumberInKey(theoreticalNote)
            val middleAngle = angleForChromaticKeyNoteNumber(noteNumberInChromaticScale)

            val textRadius = sectionRadius * .85
            val (middleX, middleY) = canvasOriginTranslator.translate(calc2DCoordinate(centerX, centerY, middleAngle, textRadius.toInt()))
            val textPaint = blackFill()
            textPaint.textSize = 24.toFloat()       // todo: text size should be based on display size
            textPaint.textAlign = Paint.Align.CENTER

            // TODO: display as sharps or flat should be optional
            canvas.drawText(noteLabelAccidentalsAsSharps(theoreticalNote), middleX.toFloat(), middleY.toFloat(), textPaint)

        }
    }

    private fun drawNoteNumbers(canvas: Canvas, canvasOriginTranslator: CanvasOriginTranslator, chromaticKey: Key, diatonicKey: Key) {
        val centerX = width / 2
        val centerY = height / 2
        val sectionRadius = Math.min(centerX, centerY)      // 1/2 the shortest dimension

        for (theoreticalNote in diatonicKey.notes) {
            val noteNumberInChromaticScale = chromaticKey.noteNumberInKey(theoreticalNote)
            val noteNumberInDiatonicKey = diatonicKey.noteNumberInKey(theoreticalNote)

            val middleAngle = angleForChromaticKeyNoteNumber(noteNumberInChromaticScale)

            val textRadius = sectionRadius * .95
            val (middleX, middleY) = canvasOriginTranslator.translate(calc2DCoordinate(centerX, centerY, middleAngle, textRadius.toInt()))
            val textPaint = blackFill()
            textPaint.textSize = 24.toFloat()       // todo: text size should be based on display size
            textPaint.textAlign = Paint.Align.CENTER
            canvas.drawText(noteNumberInDiatonicKey.toString(), middleX.toFloat(), middleY.toFloat(), textPaint)
        }
    }

    private fun drawNoteSpiralBackground(canvas: Canvas, canvasOriginTranslator: CanvasOriginTranslator, chromaticKey: Key) {
        val centerX = width / 2
        val centerY = height / 2

        val startingRadius = (Math.min(centerX, centerY) * .75).toInt()     // radius is slightly smaller than smallest dimension
        val radiusDecrement = (15 + 40) / 12    // todo: should be based on diameter of the note circles

        var lastCoordX = 0
        var lastCoordY = 0
        var decrementingRadius = startingRadius
        for (guitarNote in orebViewModel.guitar.getNoteRange()) {    // ordered from lowest to highest
            val noteNumberInChromaticScale = chromaticKey.noteNumberInKey(guitarNote.theoreticalNote)
            val angle = angleForChromaticKeyNoteNumber(noteNumberInChromaticScale)
            val untranslatedCoord = calc2DCoordinate(centerX, centerY, angle, decrementingRadius)
            val (x, y) = canvasOriginTranslator.translate(untranslatedCoord)

            if (lastCoordX != 0 && lastCoordY != 0) {
                canvas.drawLine(lastCoordX.toFloat(), lastCoordY.toFloat(), x.toFloat(), y.toFloat(), ltGreyStrokeOnePixel())
            }
            lastCoordX = x
            lastCoordY = y
            decrementingRadius -= radiusDecrement
        }
    }

    private fun drawNoteSpiralNotes(canvas: Canvas, canvasOriginTranslator: CanvasOriginTranslator, chromaticKey: Key, diatonicKey: Key) {
        val centerX = width / 2
        val centerY = height / 2

        val startingRadius = (Math.min(centerX, centerY) * .75).toInt()     // radius is slightly smaller than smallest dimension
        val radiusDecrement = (15 + 40) / 12    // todo: should be based on diameter of the note circles

        var decrementingRadius = startingRadius
        for (guitarNote in orebViewModel.guitar.getNoteRange()) {    // ordered from lowest to highest
            val noteNumberInChromaticKey = chromaticKey.noteNumberInKey(guitarNote.theoreticalNote)
            val noteNumberInDiatonicKey = diatonicKey.noteNumberInKey(guitarNote.theoreticalNote)

            val angle = angleForChromaticKeyNoteNumber(noteNumberInChromaticKey)
            val untranslatedCoord = calc2DCoordinate(centerX, centerY, angle, decrementingRadius)
            val (x, y) = canvasOriginTranslator.translate(untranslatedCoord)

            if (noteNumberInDiatonicKey > 0) {
                canvas.drawCircle(x.toFloat(), y.toFloat(), 15f, fillColorForChromaticScaleNoteNumber(noteNumberInChromaticKey))
            } else {
                canvas.drawCircle(x.toFloat(), y.toFloat(), 15f, whiteFill())
            }
            canvas.drawCircle(x.toFloat(), y.toFloat(), 15f, blackStrokeOnePixel())

            decrementingRadius -= radiusDecrement
        }
    }
}

// starts at top-center (90 deg) and goes clockwise
fun angleForChromaticKeyNoteNumber(noteNumber: Int): Int {
    return when (noteNumber) {
        1 -> 90
        2 -> 60
        3 -> 30
        4 -> 0
        5 -> 330
        6 -> 300
        7 -> 270
        8 -> 240
        9 -> 210
        10 -> 180
        11 -> 150
        12 -> 120
        else -> {
            75  // this shouldn't happen.  we return an intentionally 'off angle' so it's an obvious drawing error in case it does
        }
    }
}

fun noteLabelAccidentalsAsSharps(theoreticalNote: TheoreticalNote): String {
    var label = ""
    when (theoreticalNote) {
        TheoreticalNote.C -> label = "C"
        TheoreticalNote.C_SHARP_D_FLAT -> label = "C#"
        TheoreticalNote.D -> label = "D"
        TheoreticalNote.D_SHARP_E_FLAT -> label = "D#"
        TheoreticalNote.E -> label = "E"
        TheoreticalNote.F -> label = "F"
        TheoreticalNote.F_SHARP_G_FLAT -> label = "F#"
        TheoreticalNote.G -> label = "G"
        TheoreticalNote.G_SHARP_A_FLAT -> label = "G#"
        TheoreticalNote.A -> label = "A"
        TheoreticalNote.A_SHARP_B_FLAT -> label = "A#"
        TheoreticalNote.B -> label = "B"
    }
    return label
}

fun calc2DCoordinate(centerX: Int, centerY: Int, angleDeg: Int, radius: Int): Pair<Int, Int> {
    val angleRad = Math.toRadians(angleDeg.toDouble())
    val x = Math.round(centerX + radius * Math.cos(angleRad)).toInt()
    val y = Math.round(centerY + radius * Math.sin(angleRad)).toInt()
    return Pair(x, y)
}

fun addDegree(degree1: Int, degree2: Int): Int {
    val sum = degree1 + degree2
    return if (sum > 360) (360 - sum) else sum
}

fun subtractDegree(minuend: Int, subtrahend: Int): Int {
    val difference = minuend - subtrahend
    return if (difference < 0) (360 + difference) else difference
}