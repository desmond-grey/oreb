package com.teahousesoftware.oreb.model.music

class Scale(val stepPattern: List<Int>) {
    fun generateNotesForKey(startingNote: TheoreticalNote): List<TheoreticalNote> {
        val notesForKey = mutableListOf<TheoreticalNote>()
        val allAbstractNotes = TheoreticalNote.values().asList()

        var noteIndex = allAbstractNotes.indexOf(startingNote)
        for (numberOfHalfSteps in stepPattern) {
            val note = allAbstractNotes[noteIndex]
            notesForKey.add(note)

            noteIndex += numberOfHalfSteps

            // indexes beyond the end of the abstract-note list loop back around
            if (noteIndex >= allAbstractNotes.size)
                noteIndex = noteIndex - allAbstractNotes.size
        }

        return notesForKey
    }
}