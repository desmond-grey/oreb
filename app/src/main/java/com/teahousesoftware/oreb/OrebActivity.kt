package com.teahousesoftware.oreb

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.teahousesoftware.oreb.fragments.FretboardFragment
import com.teahousesoftware.oreb.fragments.OrebFragmentPagerAdaptor

class OrebActivity : AppCompatActivity() {
    private lateinit var orebFragmentPagerAdaptor: OrebFragmentPagerAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oreb)

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
}