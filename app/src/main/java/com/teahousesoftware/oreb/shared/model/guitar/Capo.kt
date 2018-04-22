package com.teahousesoftware.oreb.shared.model.guitar

import org.jetbrains.anko.AnkoLogger
import org.json.JSONObject

class Capo(
        val name: String,
        val stringNumFretNum: Map<Int, Int>) : AnkoLogger {

    fun getCapodFretForString(stringNumber: Int): Int {
        return stringNumFretNum[stringNumber]!!
    }

    companion object {
        const val CAPO_WIDTH = .125f

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