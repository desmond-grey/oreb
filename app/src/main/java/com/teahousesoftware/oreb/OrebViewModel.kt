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

    fun currentGuitar(): LiveData<Guitar> {
        return currentGuitar
    }

    fun setCurrentGuitar(guitar: Guitar) {
        currentGuitar.value = guitar
    }

    fun currentKey(): LiveData<TheoreticalNote> {
        return currentKey
    }

    fun setCurrentKey(key: TheoreticalNote) {
        currentKey.value = key
    }

    fun currentScale(): LiveData<Scale> {
        return currentScale
    }

    fun setCurrentScale(scale: Scale) {
        currentScale.value = scale
    }
}