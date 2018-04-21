package com.teahousesoftware.oreb.shared.model.guitar

import org.jetbrains.anko.AnkoLogger
import org.json.JSONObject

class Capo(
        val name: String,
        val stringFret: Map<Int, Int>) : AnkoLogger {

    fun getCapodFretForString(stringNumber: Int): Int {
        return stringFret[stringNumber]!!
    }

    companion object {
        fun deserialize(caposJsonString: String): Capo {
            val capoJo = JSONObject(caposJsonString)
            val capoName = capoJo.getString("name")

            val stringFret = mutableMapOf<Int, Int>()
            val stringFretJo = capoJo.getJSONObject("stringFret")
            for (stringNumberAsString in stringFretJo.keys()) {
                val stringNumber = stringNumberAsString.toInt()
                val fretNumber = stringFretJo.getInt(stringNumberAsString)
                stringFret.put(stringNumber, fretNumber)
            }

            return Capo(capoName, stringFret)
        }
    }
}