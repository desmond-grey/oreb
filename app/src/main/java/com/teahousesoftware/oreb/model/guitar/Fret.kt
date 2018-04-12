package com.teahousesoftware.oreb.model.guitar

data class Fret(
        val fretNumber: Int,
        val distanceFromNut: Float,
        val distanceFromPreviousFret: Float,
        val height: Float,
        val width: Float = .0625f)