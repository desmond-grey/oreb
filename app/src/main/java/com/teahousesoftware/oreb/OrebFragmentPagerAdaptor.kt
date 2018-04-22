package com.teahousesoftware.oreb

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.teahousesoftware.oreb.guidance.GuidanceFragment
import com.teahousesoftware.oreb.info.InfoFragment

// NOTE: FragmentPagerAdapter requires "support v4" compat fragments
class OrebFragmentPagerAdaptor(
        fragmentManager: FragmentManager,
        val orebActivity: OrebActivity) : FragmentPagerAdapter(fragmentManager) {
    companion object {
        private val NUM_ITEMS = 2
    }

    override fun getCount(): Int {
        return NUM_ITEMS
    }

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return GuidanceFragment.newInstance(
                    0,
                    orebActivity.getString(R.string.guidance_fragment_page_title)
            )
            1 -> return InfoFragment.newInstance(
                    1,
                    orebActivity.getString(R.string.info_fragment_page_title)
            )
            else -> return null
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        // TODO: don't hardcode.  Pull the title from the fragement
        when (position) {
            0 -> return orebActivity.getString(R.string.guidance_fragment_page_title)
            1 -> return orebActivity.getString(R.string.info_fragment_page_title)
            else -> return ""
        }
    }

}