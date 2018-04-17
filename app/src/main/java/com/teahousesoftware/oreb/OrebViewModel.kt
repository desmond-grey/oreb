package com.teahousesoftware.oreb

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.teahousesoftware.oreb.shared.model.guitar.Guitar
import com.teahousesoftware.oreb.shared.model.music.Scale
import com.teahousesoftware.oreb.shared.model.music.TheoreticalNote
import com.teahousesoftware.oreb.shared.model.music.Tuning

class OrebViewModel() : ViewModel() {
    lateinit var tunings:List<Tuning>
    lateinit var scales:List<Scale>

    private val currentGuitar = MutableLiveData<Guitar>()
    private val currentKey = MutableLiveData<TheoreticalNote>()
    private val currentScale = MutableLiveData<Scale>()
    private val currentTuning = MutableLiveData<Tuning>()

    fun getCurrentGuitar(): LiveData<Guitar> {
        return currentGuitar
    }

    fun setCurrentGuitar(guitar: Guitar) {
        currentGuitar.value = guitar
    }

    fun getCurrentKey(): LiveData<TheoreticalNote> {
        return currentKey
    }

    fun setCurrentKey(key: TheoreticalNote) {
        currentKey.value = key
    }

    fun getCurrentScale(): LiveData<Scale> {
        return currentScale
    }

    fun setCurrentScale(scale: Scale) {
        currentScale.value = scale
    }


    fun getCurrentTuning(): LiveData<Tuning> {
        return currentTuning
    }

    fun setCurrentTuning(tuning: Tuning) {
        currentTuning.value = tuning
    }
}