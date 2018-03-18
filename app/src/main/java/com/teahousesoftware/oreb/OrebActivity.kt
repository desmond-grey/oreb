package com.teahousesoftware.oreb

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.teahousesoftware.oreb.fragments.OrebFragmentPagerAdaptor


class OrebActivity : AppCompatActivity() {
    private lateinit var orebFragmentPagerAdaptor: OrebFragmentPagerAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oreb)

        val viewPager = findViewById(R.id.viewPager) as ViewPager
        orebFragmentPagerAdaptor = OrebFragmentPagerAdaptor(supportFragmentManager)
        viewPager.adapter = orebFragmentPagerAdaptor
    }
}