package com.teahousesoftware.oreb.shared.model.guitar

import com.teahousesoftware.oreb.shared.model.music.Tuning
import java.util.ArrayList

private const val FRET_CALCULATION_CONSTANT = 17.817f

fun generateStrings(nut: Nut, saddle: Saddle): List<GuitarString> {
    val numberOfGroovesInNut = nut.grooves.size
    val numberOfGroovesInSaddle = saddle.grooves.size

    if (numberOfGroovesInNut != numberOfGroovesInSaddle) {
        throw IllegalArgumentException("Number of grooves in nut and saddle must match.")
    }

    val guitarStrings = ArrayList<GuitarString>()

    var stringNumber = 1
    for (groove in nut.grooves) {
        guitarStrings.add(GuitarString(stringNumber, groove.distanceFromTop, saddle.xPosition - nut.xPosition))
        stringNumber++
    }

    return guitarStrings
}

fun generateFrets(scaleLength: Float, fretHeight: Float, numberOfFrets: Int): List<Fret> {
    val frets = ArrayList<Fret>()

    frets.add(Fret(0, 0f, 0f, fretHeight))  // The nut

    var cumulativeDistanceFromNut = 0f
    var remainingScaleLength = scaleLength
    for (fretNumber in 1..numberOfFrets) {
        val distanceFromPreviousFret = remainingScaleLength / FRET_CALCULATION_CONSTANT
        val distanceFromNut = cumulativeDistanceFromNut + distanceFromPreviousFret
        frets.add(Fret(fretNumber, distanceFromNut, distanceFromPreviousFret, fretHeight))

        cumulativeDistanceFromNut += distanceFromPreviousFret
        remainingScaleLength -= distanceFromPreviousFret
    }

    return frets
}

// 5% from from the top.  18% between strings.  5% from the bottom.
fun generateGroovesForSixString(height: Float): List<Groove> {
    return listOf<Groove>(
            Groove(height * .05f),      // 5% from top
            Groove(height * .23f),      // 18% from grove above
            Groove(height * .41f),      // etc..
            Groove(height * .59f),
            Groove(height * .77f),
            Groove(height * .95f)
    )
}

fun buildAndTuneLarrivee(tuning: Tuning, capo: Capo): Guitar {
    // measurements are from my Larrivee, in inches.  See readme for note about measurements
    val nut = Nut(
            2.75f,
            .125f,
            0f,
            generateGroovesForSixString(2.75f))
    val fretboard = Fretboard(
            2.75f,
            18.125f,
            2.75f,
            generateFrets(25.5f, 2.75f, 20))
    val saddle = Saddle(
            2.75f,
            .125f,
            25.5f,
            generateGroovesForSixString(2.75f))
    return Guitar(nut, fretboard, saddle, tuning, capo)
}