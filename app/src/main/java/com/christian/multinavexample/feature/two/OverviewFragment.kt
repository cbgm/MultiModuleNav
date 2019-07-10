package com.christian.multinavexample.feature.two

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.christian.multinavexample.R
import com.christian.multinavexample.feature.one.OverviewFragment

class OverviewFragment : Fragment() {
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
