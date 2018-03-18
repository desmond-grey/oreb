package com.teahousesoftware.oreb.fragments

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class OrebFragmentPagerAdaptor(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return NUM_ITEMS
    }

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return InfoFragment.newInstance(0, "Info")
            1 -> return ChordsFragment.newInstance(1, "Chords")
            else -> return null
        }
    }

    // TODO: improve how we return titles, should retrieve from fragment (it' stored in it it as an args bundle)
    override fun getPageTitle(position: Int): CharSequence {
        when (position) {
            0 -> return "Info"
            1 -> return "Chords"
            else -> return ""
        }
    }

    companion object {
        private val NUM_ITEMS = 2
    }
}