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

    val guitar = MutableLiveData<Guitar>()
    val key = MutableLiveData<TheoreticalNote>()
    val scale = MutableLiveData<Scale>()
}