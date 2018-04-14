package com.teahousesoftware.oreb.model.music

import com.teahousesoftware.oreb.model.guitar.Fret
import com.teahousesoftware.oreb.model.guitar.GuitarString

data class FrettedNote(val physicalNote: PhysicalNote, val guitarString: GuitarString, val fret: Fret)