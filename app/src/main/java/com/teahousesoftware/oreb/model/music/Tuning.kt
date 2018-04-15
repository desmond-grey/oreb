package com.teahousesoftware.oreb.model.music

import org.jetbrains.anko.AnkoLogger
import org.json.JSONObject

class Tuning(val name: String, val openStringNotes: Map<Int, PhysicalNote>) : AnkoLogger {
    fun getNoteForOpenString(stringNumber: Int): PhysicalNote? {
        return openStringNotes.get(stringNumber)
    }

    companion object Companion {
        // Normally tunings are read from assets.
        // This is a hardcoded backup that ensures the app always has one tuning.
        fun standardTuning(): Tuning {
            return Tuning(
                    "standard",
                    mapOf<Int, PhysicalNote>(
                            6 to PhysicalNote.E2,
                            5 to PhysicalNote.A2,
                            4 to PhysicalNote.D3,
                            3 to PhysicalNote.G3,
                            2 to PhysicalNote.B3,
                            1 to PhysicalNote.E4
                    )
            )
        }

        // TODO: exception handling
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