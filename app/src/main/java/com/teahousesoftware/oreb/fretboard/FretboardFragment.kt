package com.teahousesoftware.oreb.fretboard

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.teahousesoftware.oreb.OrebActivity
import com.teahousesoftware.oreb.OrebViewModel
import com.teahousesoftware.oreb.R
import com.teahousesoftware.oreb.shared.model.guitar.Guitar
import org.jetbrains.anko.AnkoLogger

// Fragments must have an empty public constructor
class FretboardFragment : Fragment(), AdapterView.OnItemSelectedListener, AnkoLogger {
    val DEFAULT_MAGNIFICATION = MAGNIFICATIONS.FIT_TO_FRETBOARD_LENGTH

    private lateinit var orebViewModel: OrebViewModel
    private lateinit var fretboardView: FretboardView

    enum class MAGNIFICATIONS(val descriptiveName: String) {
        FIT_TO_NECK_WIDTH("Fit To Neck Width"),
        FIT_TO_FRETBOARD_LENGTH("Fit To Fretboard Length")
    }

    // The activity calls this to instantiate us
    companion object {
        fun newInstance(): FretboardFragment {
            return FretboardFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        orebViewModel = ViewModelProviders.of(this.activity).get(OrebViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        // Inflate the Fretboard Fragment layout
        val fretboardLayout = inflater?.inflate(R.layout.fragment_fretboard, container, false)
        this.fretboardView = fretboardLayout?.findViewById(R.id.fretboard_view) as FretboardView

        // wire up the capo spinner
        val capoSpinner = fretboardLayout.findViewById(R.id.capo_spinner) as Spinner
        val capoSpinnerAdapter = ArrayAdapter(
                this.activity,
                android.R.layout.simple_spinner_item,
                orebViewModel.capos.map { it.name }
        )
        capoSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        capoSpinner.adapter = capoSpinnerAdapter
        capoSpinner.setOnItemSelectedListener(this)
        capoSpinner.setSelection(orebViewModel.capos.indexOf(orebViewModel.guitar.capo))

        // wire up the tuning spinner
        val tuningSpinner = fretboardLayout.findViewById(R.id.tuning_spinner) as Spinner
        val tuningSpinnerAdapter = ArrayAdapter(
                this.activity,
                android.R.layout.simple_spinner_item,
                orebViewModel.tunings.map { it.name }
        )
        tuningSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        tuningSpinner.adapter = tuningSpinnerAdapter
        tuningSpinner.setOnItemSelectedListener(this)
        tuningSpinner.setSelection(orebViewModel.tunings.indexOf(orebViewModel.guitar.tuning))  // guitars have tunings.  this syncs up the spinner with what the guitar was built with

        // wire up the magnification setting spinner
        val magnificationSpinner = fretboardLayout.findViewById(R.id.magnification_spinner) as Spinner
        val magnificationSpinnerAdapter = ArrayAdapter(
                this.activity,
                android.R.layout.simple_spinner_item,
                MAGNIFICATIONS.values().map { it.descriptiveName }
        )
        magnificationSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        magnificationSpinner.adapter = magnificationSpinnerAdapter
        magnificationSpinner.setOnItemSelectedListener(this)
        magnificationSpinner.setSelection(DEFAULT_MAGNIFICATION.ordinal)

        return fretboardLayout
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        when (parent.id) {
            R.id.capo_spinner -> consumeSpinnerEvent { capoSpinnerItemSelected(parent.getItemAtPosition(pos) as CharSequence) }
            R.id.tuning_spinner -> consumeSpinnerEvent { tuningSpinnerItemSelected(parent.getItemAtPosition(pos) as CharSequence) }
            R.id.magnification_spinner -> consumeSpinnerEvent { magnificationSpinnerItemSelected(parent.getItemAtPosition(pos) as CharSequence) }
        }
    }

    inline fun consumeSpinnerEvent(f: () -> Unit): Boolean {
        f()
        return true
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        // TODO
    }

    private fun capoSpinnerItemSelected(capoName: CharSequence) {
        val newCapo = this.orebViewModel.capos.find { it.name == capoName }!!
        val guitarWithNewCapo = this.orebViewModel.guitar.copy(capo = newCapo)
        orebViewModel.guitar = guitarWithNewCapo
        fretboardView.invalidate()
    }

    private fun tuningSpinnerItemSelected(tuningName: CharSequence) {
        val newTuning = this.orebViewModel.tunings.find { it.name == tuningName }!!
        val guitarWithNewTuning = this.orebViewModel.guitar.copy(tuning = newTuning)
        orebViewModel.guitar = guitarWithNewTuning
        fretboardView.invalidate()
    }

    private fun magnificationSpinnerItemSelected(magnificationName: CharSequence) {
        when (magnificationName) {
            MAGNIFICATIONS.FIT_TO_NECK_WIDTH.descriptiveName -> {
                val newDrawScale = calculateDrawScaleForFitToNeckWidth(
                        orebViewModel.guitar,
                        fretboardView.height
                )
                orebViewModel.drawScale = newDrawScale
                fretboardView.invalidate()
            }

            MAGNIFICATIONS.FIT_TO_FRETBOARD_LENGTH.descriptiveName -> {
                val newDrawScale = calculateDrawScaleForFitToFretboardLength(
                        orebViewModel.guitar,
                        fretboardView.width
                )
                orebViewModel.drawScale = newDrawScale
                fretboardView.invalidate()
            }
        }
    }

    private fun calculateDrawScaleForFitToNeckWidth(guitar: Guitar, viewHeightInPixels: Int): Float {
        // see README about measurement units
        return viewHeightInPixels / guitar.fretboard.heightAtSaddle
    }

    private fun calculateDrawScaleForFitToFretboardLength(guitar: Guitar, viewWidthInPixels: Int): Float {
        // see README about measurement units
        return viewWidthInPixels / guitar.fretboard.length
    }
}
