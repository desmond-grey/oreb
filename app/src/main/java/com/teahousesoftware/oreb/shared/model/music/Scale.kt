package com.teahousesoftware.oreb.shared.model.music

import org.json.JSONObject

class Scale(val name:String, val stepPattern: List<Int>) {
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

    companion object {
        fun deserialize(scaleAsJsonString: String): Scale {
            val scaleJo = JSONObject(scaleAsJsonString)
            val scaleName = scaleJo.getString("name")

            val stepPattern = mutableListOf<Int>()
            val stepPatternJa = scaleJo.getJSONArray("stepPattern")
            for (i in 0..(stepPatternJa.length() - 1)) {
                stepPattern.add(stepPatternJa.getInt(i))
            }

            return Scale(scaleName, stepPattern)
        }
    }
}