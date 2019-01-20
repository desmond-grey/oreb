package com.teahousesoftware.oreb.guidance

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teahousesoftware.oreb.R


class GuidanceFragment : Fragment() {
    private var pageNum: Int = 0
    private var title: String? = null

    companion object {
        // newInstance constructor for creating fragment with arguments
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
        pageNum = arguments!!.getInt("pageNum", 0)
        title = arguments!!.getString("title")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_guidance, container, false)
        return view
    }
}