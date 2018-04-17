package com.teahousesoftware.oreb.shared.model.music

import com.teahousesoftware.oreb.shared.model.guitar.Fret
import com.teahousesoftware.oreb.shared.model.guitar.GuitarString

data class FrettedNote(val physicalNote: PhysicalNote, val guitarString: GuitarString, val fret: Fret)