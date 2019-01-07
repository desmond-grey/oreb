package com.teahousesoftware.oreb

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.teahousesoftware.oreb.fretboard.FretboardFragment
import com.teahousesoftware.oreb.selector.SelectorView
import com.teahousesoftware.oreb.shared.model.guitar.Capo
import com.teahousesoftware.oreb.shared.model.guitar.buildAndTuneLarrivee
import com.teahousesoftware.oreb.shared.model.music.Scale
import com.teahousesoftware.oreb.shared.model.music.TheoreticalNote
import com.teahousesoftware.oreb.shared.model.music.Tuning
import org.jetbrains.anko.AnkoLogger

class OrebActivity : AppCompatActivity(), AnkoLogger {
    private lateinit var orebFragmentPagerAdaptor: OrebFragmentPagerAdaptor
    private lateinit var orebViewModel: OrebViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oreb)

        // setup the view model
        orebViewModel = ViewModelProviders.of(this).get(OrebViewModel::class.java)

        val capos = loadCaposFromAssets()
        val tunings = loadTuningsFromAssets()

        orebViewModel.capos = capos
        orebViewModel.tunings = tunings
        orebViewModel.scales = loadScalesFromAssets()

        orebViewModel.guitar = buildAndTuneLarrivee(
                tunings.find { it.name == "Standard" }!!,
                capos.find { it.name == "None" }!!)
        orebViewModel.currentTonic.value = TheoreticalNote.C     // TODO: better default handling for these two
        orebViewModel.currentScale.value = loadScalesFromAssets().find { it.name == "Major" }!!

        // load the fretboard fragment
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    // we use a backwards compatible ("support") fragment here to match what the viewPager needs ("support" fragments)
                    .replace(R.id.fragment_fretboard_placeholder, FretboardFragment.newInstance(), "Fretboard")
                    .commit()
        }

        // wire up the view pager
        val viewPager = findViewById(R.id.viewPager) as ViewPager
        orebFragmentPagerAdaptor = OrebFragmentPagerAdaptor(supportFragmentManager, this)
        viewPager.adapter = orebFragmentPagerAdaptor

        // the selector view is instantiated by the layout, but it needs us to do the observing for it
        val selectorView = findViewById(R.id.selector_view) as SelectorView
        orebViewModel.currentTonic.observe(this, Observer { selectorView.invalidate() })
        orebViewModel.currentScale.observe(this, Observer { selectorView.invalidate() })
    }

    // ----- private

    private fun loadCaposFromAssets(): List<Capo> {
        val capos = mutableListOf<Capo>()

        val caposAssetDir = "capos"
        val capoFilenames = this.assets.list(caposAssetDir).toList()
        for (capoFilename in capoFilenames) {
            val capoAsJsonString = readJsonAssetFile(caposAssetDir + "/" + capoFilename)
            capos.add(Capo.deserialize(capoAsJsonString))
        }

        return capos
    }

    private fun loadTuningsFromAssets(): List<Tuning> {
        val tunings = mutableListOf<Tuning>()

        val tuningsAssetDir = "tunings"
        val tuningFilenames = this.assets.list(tuningsAssetDir).toList()
        for (tuningFilename in tuningFilenames) {
            val tuningAsJsonString = readJsonAssetFile(tuningsAssetDir + "/" + tuningFilename)
            tunings.add(Tuning.deserialize(tuningAsJsonString))
        }

        return tunings
    }

    private fun loadScalesFromAssets(): List<Scale> {
        val scales = mutableListOf<Scale>()

        val scalesAssetDir = "scales"
        val scaleFilenames = this.assets.list(scalesAssetDir).toList()
        for (scaleFilename in scaleFilenames) {
            val scaleAsJsonString = readJsonAssetFile(scalesAssetDir + "/" + scaleFilename)
            scales.add(Scale.deserialize(scaleAsJsonString))
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