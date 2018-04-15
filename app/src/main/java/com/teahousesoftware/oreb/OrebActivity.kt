package com.teahousesoftware.oreb

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.teahousesoftware.oreb.fragments.FretboardFragment
import com.teahousesoftware.oreb.fragments.OrebFragmentPagerAdaptor
import com.teahousesoftware.oreb.model.guitar.Guitar
import com.teahousesoftware.oreb.model.guitar.buildLarrivee
import com.teahousesoftware.oreb.model.music.Tuning
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class OrebActivity : AppCompatActivity(), AnkoLogger {
    private lateinit var orebFragmentPagerAdaptor: OrebFragmentPagerAdaptor

    lateinit var tunings:List<Tuning>
    val guitar:Guitar = buildLarrivee()     // public, available to fragments and views

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oreb)

        tunings = loadTunings()

        if (savedInstanceState == null) {
            // load the fretboard fragment
            supportFragmentManager
                    .beginTransaction()
                    // we use a backwards compatible ("support") fragment here to match what the viewPager needs ("support" fragments)
                    .replace(R.id.fragment_fretboard_placeholder, FretboardFragment.newInstance(), "Fretboard")
                    .commit()
        }

        // wire up the view pager
        val viewPager = findViewById(R.id.viewPager) as ViewPager
        orebFragmentPagerAdaptor = OrebFragmentPagerAdaptor(supportFragmentManager)
        viewPager.adapter = orebFragmentPagerAdaptor
    }

    // ----- private

    // TODO: exception handling
    private fun loadTunings(): List<Tuning> {
        val tunings = mutableListOf<Tuning>()

        val tuningFilenames = this.assets.list("tunings").toList()
        for (tuningFilename in tuningFilenames) {
            val tuningFileInputStream = this.assets.open("tunings/" + tuningFilename)
            val size = tuningFileInputStream.available()
            val tuningFileBuffer = ByteArray(size)
            tuningFileInputStream.read(tuningFileBuffer)
            tuningFileInputStream.close()
            val tuningAsJsonString = String(tuningFileBuffer, Charsets.UTF_8)
            val deserializedTuning = Tuning.deserialize(tuningAsJsonString)
            tunings.add(deserializedTuning)
        }

        return tunings
    }
}