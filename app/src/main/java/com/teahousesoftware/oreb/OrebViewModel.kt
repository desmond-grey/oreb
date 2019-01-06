package com.teahousesoftware.oreb

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.teahousesoftware.oreb.shared.model.guitar.Capo
import com.teahousesoftware.oreb.shared.model.guitar.Guitar
import com.teahousesoftware.oreb.shared.model.music.Scale
import com.teahousesoftware.oreb.shared.model.music.TheoreticalNote
import com.teahousesoftware.oreb.shared.model.music.Tuning

class OrebViewModel : ViewModel() {
    // Reasonable initial values for Larrivee on Galaxy Tab S2, wide orientation
    // TODO: see measurements in the README
    val DRAW_SCALE_MIN = 75f
    val DRAW_SCALE_MAX = 250f

    lateinit var capos:List<Capo>
    lateinit var tunings:List<Tuning>
    lateinit var scales:List<Scale>

    lateinit var guitar:Guitar

    val key = MutableLiveData<TheoreticalNote>()
    val scale = MutableLiveData<Scale>()

    var drawScale: Float = DRAW_SCALE_MIN   // Driven by pinch/zoom scaling.  Start zoomed out.
}