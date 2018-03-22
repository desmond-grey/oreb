package com.teahousesoftware.oreb

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.teahousesoftware.oreb.fragments.OrebFragmentPagerAdaptor

class OrebActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var orebFragmentPagerAdaptor: OrebFragmentPagerAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oreb)

        // wire up the view pager
        val viewPager = findViewById(R.id.viewPager) as ViewPager
        orebFragmentPagerAdaptor = OrebFragmentPagerAdaptor(supportFragmentManager)
        viewPager.adapter = orebFragmentPagerAdaptor

        // wire up the tuning spinner
        val tuningSpinner = findViewById(R.id.tuning_spinner) as Spinner
        val tuningSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.tunings, android.R.layout.simple_spinner_item)
        tuningSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        tuningSpinner.adapter = tuningSpinnerAdapter
        tuningSpinner.setOnItemSelectedListener(this)

        // wire up the magnification setting
        val magnificationSpinner = findViewById(R.id.magnification_spinner) as Spinner
        val magnificationSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.magnifications, android.R.layout.simple_spinner_item)
        magnificationSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        magnificationSpinner.adapter = magnificationSpinnerAdapter
        magnificationSpinner.setOnItemSelectedListener(this)
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        when (parent.id) {
            R.id.tuning_spinner -> consume { tuningSpinnerItemSelected(parent.getItemAtPosition(pos) as CharSequence) }
            R.id.magnification_spinner -> consume { magnificationSpinnerItemSelected(parent.getItemAtPosition(pos) as CharSequence) }
        }
    }

    inline fun consume(f: () -> Unit): Boolean {
        f()
        return true
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        // TODO
    }

    private fun tuningSpinnerItemSelected(tuningName: CharSequence) {
        // TODO
    }

    private fun magnificationSpinnerItemSelected(tuningName: CharSequence) {
        // TODO
    }
}