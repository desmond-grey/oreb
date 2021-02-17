package com.teahousesoftware.oreb.shared.model.music

import com.teahousesoftware.oreb.shared.model.guitar.TheoreticalGuitarString
import org.junit.Test
import strikt.api.*
import strikt.assertions.*

@Suppress("UnusedEquals")       // the strikt .equals expression triggers a warning
class ScalePositionTest {

    @Test
    fun deserialize() {
        val scalePositionJson =
            ScalePositionTest::class.java.getResource("/ScalePosition.json").readText()
        val testScalePosition = ScalePosition.deserialize(scalePositionJson)

        expectThat(testScalePosition.name).equals("Test Position 1")
        expectThat(testScalePosition.stringScaleDegrees).hasSize(6)
        expectThat(
            testScalePosition
                .stringScaleDegrees[TheoreticalGuitarString(6)]
                ?.containsAll(listOf(ScaleDegree.FOUR, ScaleDegree.FIVE, ScaleDegree.SIX))
        )
        expectThat(
            testScalePosition
                .stringScaleDegrees[TheoreticalGuitarString(5)]
                ?.containsAll(listOf(ScaleDegree.SEVEN, ScaleDegree.ONE, ScaleDegree.TWO))
        )
        expectThat(
            testScalePosition
                .stringScaleDegrees[TheoreticalGuitarString(4)]
                ?.containsAll(listOf(ScaleDegree.THREE, ScaleDegree.FOUR, ScaleDegree.FIVE))
        )
        expectThat(
            testScalePosition
                .stringScaleDegrees[TheoreticalGuitarString(3)]
                ?.containsAll(listOf(ScaleDegree.SIX, ScaleDegree.SEVEN, ScaleDegree.ONE))
        )
        expectThat(
            testScalePosition
                .stringScaleDegrees[TheoreticalGuitarString(2)]
                ?.containsAll(listOf(ScaleDegree.TWO, ScaleDegree.THREE))
        )
        expectThat(
            testScalePosition
                .stringScaleDegrees[TheoreticalGuitarString(1)]
                ?.containsAll(listOf(ScaleDegree.FOUR, ScaleDegree.FIVE, ScaleDegree.SIX))
        )
    }
}