package com.teahousesoftware.oreb.shared.model.guitar

class GuitarString(
    stringNumber: Int,
    var yPosition: Float,
    var length: Float
) : TheoreticalGuitarString(stringNumber) {


    /*
        Use only the string number to determine equality.  We rely on this when comparing
        GuitarString's on the fretboard (which have yPostion and length) and
        TheoreticalGuitarStrings used to key the ScalePosition maps.
    */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GuitarString

        if (stringNumber != other.stringNumber) return false

        return true
    }

    override fun hashCode(): Int {
        return stringNumber
    }
}