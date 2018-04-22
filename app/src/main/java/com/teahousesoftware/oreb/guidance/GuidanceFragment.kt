package com.teahousesoftware.oreb.guidance

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


class GuidanceFragment : Fragment(), AdapterView.OnItemSelectedListener {
    val DEFAULT_KEY = TheoreticalNote.C
    val DEFAULT_SCALE_NAME = "Major"                // TODO: do not hardcode string

    private lateinit var orebViewModel: OrebViewModel

    private var pageNum: Int = 0
    private var title: String? = null

    companion object {
        fun newInstance(page: Int, title: String): GuidanceFragment {
            val guidanceFragment = GuidanceFragment()
            val args = Bundle()
            args.putInt("pageNum", page)
            args.putString("title", title)
            guidanceFragment.arguments = args
            return guidanceFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageNum = arguments.getInt("pageNum", 0)
        title = arguments.getString("title")
        orebViewModel = ViewModelProviders.of(this.activity).get(OrebViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val layout = inflater!!.inflate(R.layout.fragment_guidance, container, false)

        // wire up the key spinner
        val keySpinner = layout.findViewById(R.id.key_spinner) as Spinner
        val keySpinnerAdapter = ArrayAdapter(
                this.activity,
                android.R.layout.simple_spinner_item,
                TheoreticalNote.values().map { it.descriptiveName }
        )
        keySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        keySpinner.adapter = keySpinnerAdapter
        keySpinner.setOnItemSelectedListener(this)
        keySpinner.setSelection(DEFAULT_KEY.ordinal)
        
        // wire up the scale spinner
        val scaleSpinner = layout.findViewById(R.id.scale_spinner) as Spinner
        val scaleSpinnerAdapter = ArrayAdapter(
                this.activity,
                android.R.layout.simple_spinner_item,
                orebViewModel.scales.map { it.name }
        )
        scaleSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        scaleSpinner.adapter = scaleSpinnerAdapter
        scaleSpinner.setOnItemSelectedListener(this)
        scaleSpinner.setSelection(orebViewModel.scales.indexOfFirst { it.name == DEFAULT_SCALE_NAME })

        return layout
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        when (parent.id) {
            R.id.key_spinner -> consumeSpinnerEvent { keySpinnerItemSelected(parent.getItemAtPosition(pos) as CharSequence) }
            R.id.scale_spinner -> consumeSpinnerEvent { scaleSpinnerItemSelected(parent.getItemAtPosition(pos) as CharSequence) }
        }
    }

    inline fun consumeSpinnerEvent(f: () -> Unit): Boolean {
        f()
        return true
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        // TODO
    }

    private fun keySpinnerItemSelected(keyName: CharSequence) {
        orebViewModel.key.value = TheoreticalNote.values().find { it.descriptiveName == keyName }!!
    }

    private fun scaleSpinnerItemSelected(scaleName: CharSequence) {
        orebViewModel.scale.value = orebViewModel.scales.find { it.name == scaleName }!!
    }
}