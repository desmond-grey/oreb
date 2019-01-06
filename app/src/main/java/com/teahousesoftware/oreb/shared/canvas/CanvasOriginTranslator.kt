package com.teahousesoftware.oreb.shared.canvas

import org.jetbrains.anko.AnkoLogger

// A normal Android canvas has its 0,0 origin at the upper left, but trig calcs assume it's at the lower-left
// This translates lower-left-origin coords (from trig) to a upper-left-origin coords (for the canvas)
class CanvasOriginTranslator(@Suppress("unused") val canvasWidth: Int, val canvasHeight: Int) : AnkoLogger {
    fun translate(untranslatedCoords: Pair<Int, Int>): Pair<Int, Int> {
        val translatedX = untranslatedCoords.first      // no translation needed for x
        val translatedY = canvasHeight - untranslatedCoords.second
        return Pair(translatedX, translatedY)
    }
}