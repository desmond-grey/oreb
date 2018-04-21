package com.teahousesoftware.oreb.shared.model.music

import org.jetbrains.anko.AnkoLogger
import org.json.JSONObject

class Tuning(
        val name: String,
        val openStringNotes: Map<Int, PhysicalNote>) : AnkoLogger {

    fun getNoteForOpenString(stringNumber: Int): PhysicalNote? {
        return openStringNotes.get(stringNumber)
    }

    companion object {
        fun deserialize(tuningsJsonString: String): Tuning {
            val tuningJo = JSONObject(tuningsJsonString)
            val tuningName = tuningJo.getString("name")

            val openStringNotes = mutableMapOf<Int, PhysicalNote>()
            val openStringNotesJo = tuningJo.getJSONObject("openStringNotes")
            for (stringNumberAsString in openStringNotesJo.keys()) {
                val stringNumber = stringNumberAsString.toInt()
                val physicalNote = PhysicalNote.valueOf(openStringNotesJo.getString(stringNumberAsString))
                openStringNotes.put(stringNumber, physicalNote)
            }

            return Tuning(tuningName, openStringNotes)
        }
    }
}