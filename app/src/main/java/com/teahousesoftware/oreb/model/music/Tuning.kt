package com.teahousesoftware.oreb.model.music

import org.jetbrains.anko.AnkoLogger
import org.json.JSONObject

class Tuning(val name:String, val openStringNotes: Map<Int, PhysicalNote>) : AnkoLogger {
    fun getOpenStringNoteForString(stringNumber: Int): PhysicalNote? {
        return openStringNotes.get(stringNumber)
    }

    // TODO: exception handling
    companion object Deserializer {
        fun deserialize(tuningsJsonString: String): Tuning {
            val tuningsJo = JSONObject(tuningsJsonString)
            val tuningName = tuningsJo.getString("name")

            val openStringNotes = mutableMapOf<Int, PhysicalNote>()
            val openStringNotesJo = tuningsJo.getJSONObject("openStringNotes")
            for (stringNumberAsString in openStringNotesJo.keys()) {
                val stringNumber = stringNumberAsString.toInt()
                val physicalNote = PhysicalNote.valueOf(openStringNotesJo.getString(stringNumberAsString))
                openStringNotes.put(stringNumber, physicalNote)
            }

            return Tuning(tuningName, openStringNotes)
        }
    }
}