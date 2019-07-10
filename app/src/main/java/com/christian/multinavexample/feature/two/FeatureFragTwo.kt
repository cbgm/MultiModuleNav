package com.christian.multinavexample.feature.two

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.christian.multinavexample.MainCoordinatorImpl
import com.christian.multinavexample.R
import com.christian.multinavlib.ui.FeatureFragment


class FeatureFragTwo : FeatureFragment(MainCoordinatorImpl.States.FEATURE_TWO) {

    companion object {
        fun newInstance() = FeatureFragTwo()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feature_frag_two, container, false)
    }
}
