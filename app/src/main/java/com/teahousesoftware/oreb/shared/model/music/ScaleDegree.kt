package com.teahousesoftware.oreb.shared.model.music

enum class ScaleDegree constructor(val scaleDegreeInteger: Int) {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7);

    companion object {
        private val map = values().associateBy(ScaleDegree::scaleDegreeInteger)
        fun fromInt(scaleDegreeAsInt: Int) = map[scaleDegreeAsInt]
    }
}