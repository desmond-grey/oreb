package com.teahousesoftware.oreb.shared.model.music

import org.jetbrains.anko.AnkoLogger

class Key(val tonic:TheoreticalNote, val scale: Scale) : AnkoLogger {
    val notes: List<TheoreticalNote> = generateNotes(tonic, scale)

    fun noteNumberInKey(note: TheoreticalNote): Int {
        val noteNumberZeroBased = notes.indexOf(note)

        // convert to one-based
        return if (noteNumberZeroBased >= 0) {
            noteNumberZeroBased + 1
        } else {
            -1
        }
    }
}

private fun generateNotes(tonic: TheoreticalNote, scale: Scale): List<TheoreticalNote> {
    val notesForKey = mutableListOf<TheoreticalNote>()
    val allAbstractNotes = TheoreticalNote.values().asList()
    var noteIndex = allAbstractNotes.indexOf(tonic)
    for (numberOfHalfSteps in scale.stepPattern) {
        val note = allAbstractNotes[noteIndex]
        notesForKey.add(note)

        noteIndex += numberOfHalfSteps

        // indexes beyond the end of the abstract-note list loop back around
        if (noteIndex >= allAbstractNotes.size)
            noteIndex = noteIndex - allAbstractNotes.size
    }

    return notesForKey
}