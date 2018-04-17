package com.teahousesoftware.oreb.shared.model.guitar

data class Fretboard(
        val heightAtNut: Float,
        val length: Float,
        val heightAtSaddle: Float,
        val frets: List<Fret>)