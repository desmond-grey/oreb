package com.teahousesoftware.oreb.scalepositions

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.teahousesoftware.oreb.R
import org.jetbrains.anko.AnkoLogger


class ScalePositionsFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private var pageNum: Int = 0
    private var title: String? = null

    companion object {
        // newInstance constructor for creating fragment with arguments
        fun newInstance(page: Int, title: String): ScalePositionsFragment {
            val scalePositionsFragment = ScalePositionsFragment()
            val args = Bundle()
            args.putInt("pageNum", page)
            args.putString("title", title)
            scalePositionsFragment.arguments = args
            return scalePositionsFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageNum = arguments!!.getInt("pageNum", 0)
        title = arguments!!.getString("title")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val scalePositionsLayout =
            inflater.inflate(R.layout.fragment_scale_positions, container, false)

        // wire up the scalePositionSystem spinner
        val scalePositionSystemSpinner =
            scalePositionsLayout.findViewById(R.id.scale_position_system_spinner) as Spinner
        val scalePositionSystemSpinnerAdapter = ArrayAdapter(
            this.activity,
            R.layout.oreb_spinner_item,
            arrayOf("placeholder 1", "placeholder 2")
        )
        scalePositionSystemSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        scalePositionSystemSpinner.adapter = scalePositionSystemSpinnerAdapter
        scalePositionSystemSpinner.setOnItemSelectedListener(this)
        scalePositionSystemSpinner.setSelection(1)

        // wire up the scalePosition spinner
        val scalePositionSpinner =
            scalePositionsLayout.findViewById(R.id.scale_position_spinner) as Spinner
        val scalePositionSpinnerAdapter = ArrayAdapter(
            this.activity,
            R.layout.oreb_spinner_item,
            arrayOf("Scale Position #1", "Scale Position #2")
        )
        scalePositionSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        scalePositionSpinner.adapter = scalePositionSpinnerAdapter
        scalePositionSpinner.setOnItemSelectedListener(this)
        scalePositionSpinner.setSelection(1)

        return scalePositionsLayout
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
//        when (parent.id) {
//            R.id.tonic_spinner -> consumeSpinnerEvent { keySpinnerItemSelected(parent.getItemAtPosition(pos) as CharSequence) }
//            R.id.scale_spinner -> consumeSpinnerEvent { scaleSpinnerItemSelected(parent.getItemAtPosition(pos) as CharSequence) }
//        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        // TODO
    }
}