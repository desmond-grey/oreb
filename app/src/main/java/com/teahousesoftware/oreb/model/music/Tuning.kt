package com.teahousesoftware.oreb.model.music

class Tuning(val openStringNotes: Map<Int, PhysicalNote>) {
    fun getOpenStringNoteForString(stringNumber: Int): PhysicalNote? {
        return openStringNotes.get(stringNumber)
    }
}