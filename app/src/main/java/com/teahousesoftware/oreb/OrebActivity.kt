package com.teahousesoftware.oreb

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.teahousesoftware.oreb.fragments.FretboardFragment
import com.teahousesoftware.oreb.fragments.OrebFragmentPagerAdaptor
import com.teahousesoftware.oreb.model.guitar.Guitar
import com.teahousesoftware.oreb.model.guitar.buildAndTuneLarrivee
import com.teahousesoftware.oreb.model.music.Tuning
import org.jetbrains.anko.AnkoLogger

class OrebActivity : AppCompatActivity(), AnkoLogger {
    private lateinit var orebFragmentPagerAdaptor: OrebFragmentPagerAdaptor

    lateinit var tunings:List<Tuning>
    lateinit var guitar:Guitar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oreb)

        tunings = loadTuningsFromAssets()
        val defaultTuning:Tuning = tunings.find { it.name == "Standard" } ?: Tuning.defaultTuning()
        guitar = buildAndTuneLarrivee(defaultTuning)

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
    private fun loadTuningsFromAssets(): List<Tuning> {
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