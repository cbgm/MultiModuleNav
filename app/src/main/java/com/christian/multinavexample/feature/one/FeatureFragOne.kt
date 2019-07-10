package com.christian.multinavexample.feature.one

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.christian.multinavexample.MainCoordinatorImpl
import com.christian.multinavexample.R
import com.christian.multinavlib.ui.FeatureFragment


class FeatureFragOne : FeatureFragment(MainCoordinatorImpl.States.FEATURE_ONE) {

    companion object {
        fun newInstance() = FeatureFragOne()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feature_frag_one, container, false)
    }
}
