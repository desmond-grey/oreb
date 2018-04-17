package com.teahousesoftware.oreb

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.teahousesoftware.oreb.guidance.GuidanceFragment
import com.teahousesoftware.oreb.info.InfoFragment

// NOTE: FragmentPagerAdapter requires "support v4" compat fragments
class OrebFragmentPagerAdaptor(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return NUM_ITEMS
    }

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return InfoFragment.newInstance(0, "Info")
            1 -> return GuidanceFragment.newInstance(1, "Guidance")
            else -> return null
        }
    }

    // TODO: improve how we return titles, should retrieve from fragment (it's stored in it as an args bundle)
    override fun getPageTitle(position: Int): CharSequence {
        when (position) {
            0 -> return "Info"
            1 -> return "Guidance"
            else -> return ""
        }
    }

    companion object {
        private val NUM_ITEMS = 2
    }
}