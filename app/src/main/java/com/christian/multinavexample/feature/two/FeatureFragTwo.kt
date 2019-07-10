package com.christian.multinavexample.feature.two

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.christian.multinavexample.ExampleCoordinatorManager
import com.christian.multinavexample.R


class FeatureFragTwo : Fragment() {
    private val coordinatorManager = ExampleCoordinatorManager

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coordinatorManager.switchFeatureCoordinator(ExampleCoordinatorManager.States.FEATURE_TWO, this)
    }
}
