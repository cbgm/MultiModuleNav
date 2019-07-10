package com.christian.multinavexample.feature.two

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.christian.multinavexample.R
import com.christian.multinavexample.feature.one.OverviewFragment
import com.christian.multinavlib.ui.SubFeatureFragment

class OverviewFragment : SubFeatureFragment() {
    companion object {
        fun newInstance() = OverviewFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_overview2, container, false)
    }
}
