package com.teahousesoftware.oreb.shared.model.music

import com.teahousesoftware.oreb.shared.model.guitar.TheoreticalGuitarString
import org.json.JSONObject


class ScalePosition(
    val name: String,
    val stringScaleDegrees: Map<TheoreticalGuitarString, List<ScaleDegree>>
) {

    companion object {
        fun deserialize(scalePositionAsJsonString: String): ScalePosition {
            val scalePositionJo = JSONObject(scalePositionAsJsonString)
            return deserializeScalePositionFromJsonObject(scalePositionJo)
        }

        fun deserializeScalePositionFromJsonObject(scalePositionJo: JSONObject): ScalePosition {
            val stringScaleDegreesJo = scalePositionJo.getJSONObject("string_scale_degrees")

            val scalePositionName = scalePositionJo.getString("name")
            val stringScaleDegrees = mutableMapOf<TheoreticalGuitarString, List<ScaleDegree>>()

            for (guitarStringName in stringScaleDegreesJo.keys()) {
                val scaleDegreesForGuitarStringJo = stringScaleDegreesJo.getJSONArray(guitarStringName)

                val guitarStringNumber = guitarStringName.removePrefix("string_").toInt()
                val guitarString = TheoreticalGuitarString(guitarStringNumber)
                val scaleDegreesForGuitarString = mutableListOf<ScaleDegree>()
                for (i in 0 until scaleDegreesForGuitarStringJo.length()) {
                    val scaleDegreeAsInt = scaleDegreesForGuitarStringJo[i] as Int
                    val scaleDegree = ScaleDegree.fromInt(scaleDegreeAsInt)
                    if (scaleDegree != null) {
                        scaleDegreesForGuitarString.add(scaleDegree)
                    }
                }

                stringScaleDegrees.put(guitarString, scaleDegreesForGuitarString)
            }

            return ScalePosition(scalePositionName, stringScaleDegrees)
        }
    }
}