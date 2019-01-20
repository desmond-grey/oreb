package com.teahousesoftware.oreb.selector

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.teahousesoftware.oreb.OrebViewModel
import com.teahousesoftware.oreb.R
import com.teahousesoftware.oreb.shared.model.music.TheoreticalNote
import org.jetbrains.anko.AnkoLogger

// Fragments must have an empty public constructor
class SelectorFragment : Fragment(), AdapterView.OnItemSelectedListener, AnkoLogger {
    val DEFAULT_KEY = TheoreticalNote.C
    val DEFAULT_SCALE_NAME = "Major"                // TODO: do not hardcode string

    private lateinit var orebViewModel: OrebViewModel
    private lateinit var selectorView: SelectorView

    // The activity calls this to instantiate us
    companion object {
        fun newInstance(): SelectorFragment {
            return SelectorFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        orebViewModel = ViewModelProviders.of(this.activity!!).get(OrebViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Inflate the Selector Fragment layout
        val selectorLayout = inflater.inflate(R.layout.fragment_selector, container, false)
        this.selectorView = selectorLayout?.findViewById(R.id.selector_view) as SelectorView

        // wire up the currentTonic spinner
        val tonicSpinner = selectorLayout.findViewById(R.id.tonic_spinner) as Spinner
        val tonicSpinnerAdapter = ArrayAdapter(
            this.activity,
            R.layout.oreb_spinner_item,
            TheoreticalNote.values().map { it.descriptiveName }
        )
        tonicSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        tonicSpinner.adapter = tonicSpinnerAdapter
        tonicSpinner.setOnItemSelectedListener(this)
        tonicSpinner.setSelection(DEFAULT_KEY.ordinal)

        // wire up the currentScale spinner
        val scaleSpinner = selectorLayout.findViewById(R.id.scale_spinner) as Spinner
        val scaleSpinnerAdapter = ArrayAdapter(
            this.activity,
            R.layout.oreb_spinner_item,
            orebViewModel.scales.map { it.name }
        )
        scaleSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        scaleSpinner.adapter = scaleSpinnerAdapter
        scaleSpinner.setOnItemSelectedListener(this)
        scaleSpinner.setSelection(orebViewModel.scales.indexOfFirst { it.name == DEFAULT_SCALE_NAME })

        // respond to changes in guidance:currentTonic and guidance:currentScale spinners
        orebViewModel.currentTonic.observe(this, Observer { selectorView.invalidate() })
        orebViewModel.currentScale.observe(this, Observer { selectorView.invalidate() })

        return selectorLayout
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        when (parent.id) {
            R.id.tonic_spinner -> consumeSpinnerEvent { keySpinnerItemSelected(parent.getItemAtPosition(pos) as CharSequence) }
            R.id.scale_spinner -> consumeSpinnerEvent { scaleSpinnerItemSelected(parent.getItemAtPosition(pos) as CharSequence) }
        }
    }

    private inline fun consumeSpinnerEvent(f: () -> Unit): Boolean {
        f()
        return true
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        // TODO
    }

    private fun keySpinnerItemSelected(keyName: CharSequence) {
        orebViewModel.currentTonic.value = TheoreticalNote.values().find { it.descriptiveName == keyName }!!
    }

    private fun scaleSpinnerItemSelected(scaleName: CharSequence) {
        orebViewModel.currentScale.value = orebViewModel.scales.find { it.name == scaleName }!!
    }

}
