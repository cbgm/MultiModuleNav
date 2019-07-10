package com.christian.multinavexample.feature.one

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.christian.multinavexample.ExampleCoordinatorManager
import com.christian.multinavexample.R


class FeatureFragOne : Fragment() {
    private val coordinatorManager = ExampleCoordinatorManager

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coordinatorManager.switchFeatureCoordinator(ExampleCoordinatorManager.States.FEATURE_ONE, this)
    }
}
