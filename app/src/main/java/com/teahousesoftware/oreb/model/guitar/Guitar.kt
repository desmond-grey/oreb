package com.teahousesoftware.oreb.model.guitar

class Guitar(
        val nut: Nut,
        val fretboard: Fretboard,
        val saddle: Saddle) {
    val scaleLength:Float = this.saddle.xPosition - this.nut.xPosition
    val strings: List<GuitarString> =  generateStrings(this.nut, this.saddle)
}