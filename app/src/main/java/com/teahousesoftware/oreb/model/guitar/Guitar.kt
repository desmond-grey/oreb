package com.teahousesoftware.oreb.model.guitar

import com.google.common.collect.HashBasedTable
import com.teahousesoftware.oreb.model.music.PhysicalNote
import com.teahousesoftware.oreb.model.music.Tuning

class Guitar(
        val nut: Nut,
        val fretboard: Fretboard,
        val saddle: Saddle,
        val tuning: Tuning) {
    val scaleLength:Float = this.saddle.xPosition - this.nut.xPosition
    val strings: List<GuitarString> = generateStrings(this.nut, this.saddle)
    val noteTable:HashBasedTable<GuitarString, Fret, PhysicalNote> = HashBasedTable.create()

    init {
        val allPhysicalNotes = PhysicalNote.values()
        for (string in strings) {
            val firstNote = tuning.getNoteForOpenString(string.stringNumber)!!  // NPE is string not found

            var noteOrdinal = firstNote.ordinal
            for (fret in fretboard.frets) {
                val thisNote = allPhysicalNotes[noteOrdinal]
                noteTable.put(string, fret, thisNote)

                noteOrdinal++
            }
        }
    }
}