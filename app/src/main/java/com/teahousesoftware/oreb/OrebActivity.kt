package com.teahousesoftware.oreb

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.teahousesoftware.oreb.fretboard.FretboardFragment
import com.teahousesoftware.oreb.shared.model.guitar.Guitar
import com.teahousesoftware.oreb.shared.model.guitar.buildAndTuneLarrivee
import com.teahousesoftware.oreb.shared.model.music.Scale
import com.teahousesoftware.oreb.shared.model.music.TheoreticalNote
import com.teahousesoftware.oreb.shared.model.music.Tuning
import org.jetbrains.anko.AnkoLogger

class OrebActivity : AppCompatActivity(), AnkoLogger {
    private lateinit var orebFragmentPagerAdaptor: OrebFragmentPagerAdaptor
    private lateinit var orebViewModel: OrebViewModel

    // these are referenced by fragments and views
    lateinit var tunings:List<Tuning>
    lateinit var scales:List<Scale>
    lateinit var guitar:Guitar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oreb)

        tunings = loadTuningsFromAssets()
        scales = loadScalesFromAssets()
        guitar = buildAndTuneLarrivee(tunings.find { it.name == "Standard" }!!)

        // setup the view model
        orebViewModel = ViewModelProviders.of(this).get(OrebViewModel::class.java)
        orebViewModel.tunings = tunings
        orebViewModel.scales = scales

        orebViewModel.guitar.value = guitar
        orebViewModel.key.value = TheoreticalNote.C
        orebViewModel.scale.value = scales.find { it.name == "Major" }!!

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

    private fun loadTuningsFromAssets(): List<Tuning> {
        val tunings = mutableListOf<Tuning>()

        val tuningsAssetDir = "tunings"
        val tuningFilenames = this.assets.list(tuningsAssetDir).toList()
        for (tuningFilename in tuningFilenames) {
            val tuningAsJsonString = readJsonAssetFile(tuningsAssetDir + "/" + tuningFilename)
            val deserializedTuning = Tuning.deserialize(tuningAsJsonString)
            tunings.add(deserializedTuning)
        }

        return tunings
    }

    private fun loadScalesFromAssets(): List<Scale> {
        val scales = mutableListOf<Scale>()

        val scalesAssetDir = "scales"
        val scaleFilenames = this.assets.list(scalesAssetDir).toList()
        for (scaleFilename in scaleFilenames) {
            val scaleAsJsonString = readJsonAssetFile(scalesAssetDir + "/" + scaleFilename)
            val deserializedscale = Scale.deserialize(scaleAsJsonString)
            scales.add(deserializedscale)
        }

        return scales
    }

    private fun readJsonAssetFile(filename:String): String {
        val inputStream = this.assets.open(filename)
        val size = inputStream.available()
        val fileBuffer = ByteArray(size)
        inputStream.read(fileBuffer)
        inputStream.close()
        return String(fileBuffer, Charsets.UTF_8)
    }
 }