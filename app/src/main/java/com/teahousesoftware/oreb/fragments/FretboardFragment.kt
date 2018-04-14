package com.teahousesoftware.oreb.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.teahousesoftware.oreb.R

// Fragments must have an empty public constructor.  No constructor here, so we get default-generated empty.  Perfect.
class FretboardFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private val TUNING_FILENAME_PREFIX = "Tuning"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        fun newInstance(): FretboardFragment {
            return FretboardFragment()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        // Inflate the Fretboard view
        val fretboardView = inflater?.inflate(R.layout.fragment_fretboard, container, false)

        // wire up the tuning spinner
        val tuningSpinner = fretboardView?.findViewById(R.id.tuning_spinner) as Spinner
        val tuningSpinnerAdapter = ArrayAdapter(
                this.activity,
                android.R.layout.simple_spinner_item,
                activity.assets.list("/tunings").toList().map { it.removeSuffix(".json")})
        tuningSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        tuningSpinner.adapter = tuningSpinnerAdapter
        tuningSpinner.setOnItemSelectedListener(this)

        // wire up the magnification setting spinner
        val magnificationSpinner = fretboardView.findViewById(R.id.magnification_spinner) as Spinner
        val magnificationSpinnerAdapter = ArrayAdapter.createFromResource(this.activity, R.array.magnifications, android.R.layout.simple_spinner_item)
        magnificationSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        magnificationSpinner.adapter = magnificationSpinnerAdapter
        magnificationSpinner.setOnItemSelectedListener(this)

        return fretboardView
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        when (parent.id) {
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

    private fun tuningSpinnerItemSelected(@Suppress("UNUSED_PARAMETER") tuningName: CharSequence) {
        // TODO
    }

    private fun magnificationSpinnerItemSelected(@Suppress("UNUSED_PARAMETER") tuningName: CharSequence) {
        // TODO
    }

}
