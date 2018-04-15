package com.teahousesoftware.oreb.model.guitar

import com.teahousesoftware.oreb.model.music.Tuning

class Guitar(
        val nut: Nut,
        val fretboard: Fretboard,
        val saddle: Saddle,
        val tuning: Tuning) {
    val scaleLength:Float = this.saddle.xPosition - this.nut.xPosition
    val strings: List<GuitarString> =  generateStrings(this.nut, this.saddle)
}