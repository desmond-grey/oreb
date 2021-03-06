package com.teahousesoftware.oreb.shared.model.guitar

import com.google.common.collect.HashBasedTable
import com.teahousesoftware.oreb.shared.model.music.PhysicalNote
import com.teahousesoftware.oreb.shared.model.music.Tuning
import org.jetbrains.anko.AnkoLogger

data class Guitar(
        val nut: Nut,
        val fretboard: Fretboard,
        val saddle: Saddle,
        val tuning: Tuning,
        val capo:Capo) {

    val scaleLength:Float = this.saddle.xPosition - this.nut.xPosition
    val strings: List<GuitarString> = generateStrings(this.nut, this.saddle)
    val noteTable: HashBasedTable<GuitarString, Fret, PhysicalNote> = HashBasedTable.create()

    init {
        val allPhysicalNotes = PhysicalNote.values()
        for (string in strings) {
            val firstNote = tuning.getNoteForOpenString(string.stringNumber)!!

            var noteOrdinal = firstNote.ordinal
            for (fret in fretboard.frets) {
                if (fret.fretNumber >= capo.getCapodFretForString(string.stringNumber)) {
                    val thisNote = allPhysicalNotes[noteOrdinal]
                    noteTable.put(string, fret, thisNote)
                }

                noteOrdinal++
            }
        }
    }

    fun getNoteRange(): List<PhysicalNote> {
        val noteList = mutableListOf<PhysicalNote>()

        // extract from guava table to kotlin list
        for (cell in noteTable.cellSet()) {
            val physicalNote = cell.value
            physicalNote?.let {         // kotlin idiom for null handling
                noteList.add(physicalNote)
            }
        }

        return noteList.distinct().sorted()
    }
}