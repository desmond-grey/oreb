package com.teahousesoftware.oreb.shared.model.music

import org.json.JSONArray
import org.json.JSONObject

class ScalePositionSystem(
    val name: String,
    val description: String,
    val compatibleTunings: List<Tuning>,
    val compatibleScales: List<Scale>,
    val scalePositions: List<ScalePosition>
) {
    companion object {
        fun deserialize(
            scalePositionSystemAsRawJson: String,
            allTunings: List<Tuning>,
            allScales: List<Scale>
        ): ScalePositionSystem {
            val scalePositionSystemJo = JSONObject(scalePositionSystemAsRawJson)

            return ScalePositionSystem(
                scalePositionSystemJo.getString("name"),
                scalePositionSystemJo.getString("description"),
                deserializeCompatibleTunings(scalePositionSystemJo, allTunings),
                deserializeCompatibleScales(scalePositionSystemJo, allScales),
                deserializeScalePositions(scalePositionSystemJo)
            )
        }

        private fun deserializeCompatibleTunings(
            scalePositionSystemJo: JSONObject,
            allTunings: List<Tuning>
        ): List<Tuning> {
            val compatibleTuningsJa: JSONArray = scalePositionSystemJo.getJSONArray("compatibleTunings")

            val compatibleTunings = mutableListOf<Tuning>()
            for (i in 0 until compatibleTuningsJa.length()) {
                val tuningName: String = compatibleTuningsJa.getString(i)
                val compatibleTuning: Tuning = allTunings.single { it.name == tuningName }
                compatibleTunings.add(compatibleTuning)
            }
            return compatibleTunings
        }

        private fun deserializeCompatibleScales(
            scalePositionSystemJo: JSONObject,
            allScales: List<Scale>
        ): List<Scale> {
            val compatibleScalesJa: JSONArray = scalePositionSystemJo.getJSONArray("compatibleScales")

            val compatibleScales = mutableListOf<Scale>()
            for (i in 0 until compatibleScalesJa.length()) {
                val scaleName: String = compatibleScalesJa.getString(i)
                val compatibleScale: Scale = allScales.single { it.name == scaleName }
                compatibleScales.add(compatibleScale)
            }
            return compatibleScales
        }

        private fun deserializeScalePositions(scalePositionSystemJo: JSONObject): List<ScalePosition> {
            val scalePositionsJa: JSONArray = scalePositionSystemJo.getJSONArray("scalePositions")

            val scalePositions = mutableListOf<ScalePosition>()
            for (i in 0 until scalePositionsJa.length()) {
                val scalePositionJo: JSONObject = scalePositionsJa.getJSONObject(i)
                val scalePosition = ScalePosition.deserializeScalePositionFromJsonObject(scalePositionJo)
                scalePositions.add(scalePosition)
            }
            return scalePositions
        }
    }
}