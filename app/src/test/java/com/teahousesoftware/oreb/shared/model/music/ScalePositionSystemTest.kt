package com.teahousesoftware.oreb.shared.model.music

import org.junit.Test
import strikt.api.*
import strikt.assertions.*

@Suppress("UnusedEquals")   // the strikt .equals expression triggers a warning
class ScalePositionSystemTest {

    @Test
    fun deserialize() {
        val scalePositionSystemJson =  ScalePositionSystemTest::class.java
            .getResource("/ScalePositionSystem.json")   // for this test we use a copy of the "7 Positions/Berklee System"
            .readText()

        val allTestTunings = generateAllTestTunings()
        val allTestScales = generateAllTestScales()

        val testScalePositionSystem = ScalePositionSystem.deserialize(
            scalePositionSystemJson,
            allTestTunings,
            allTestScales
        )

        expectThat(testScalePositionSystem.name).equals("7 Positions/Berklee System")

        expectThat(testScalePositionSystem.compatibleScales.size).equals(1)
        expectThat(testScalePositionSystem.compatibleScales.first().name).equals("Standard")

        expectThat(testScalePositionSystem.compatibleTunings).equals(1)
        expectThat(testScalePositionSystem.compatibleTunings.first()).equals("Major Scale")
    }

    // we're only testing tuning-lookup during deserialization so we don't need "real" tunings
    private fun generateAllTestTunings(): List<Tuning> {
        return listOf(
            Tuning("Standard", mapOf(1 to PhysicalNote.A0)),
            Tuning("test tuning 2", mapOf(2 to PhysicalNote.A1))
        )
    }

    // we're only testing scale-lookup during deserialization so we don't need "real" scales
    private fun generateAllTestScales(): List<Scale> {
        return listOf(
            Scale("Major Scale", listOf(2, 2, 1)),
            Scale("test scale 2", listOf(2, 2, 2))
        )
    }
}