package com.christian.multinavexample.feature.two

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.christian.multinavexample.ExampleCoordinatorManager
import com.christian.multinavexample.R
import com.christian.multinavexample.feature.two.nav.FlowCoordinator
import kotlinx.android.synthetic.main.fragment_overview_two.*


class OverviewTwoFragment : Fragment() {
    private val coordinatorManager = ExampleCoordinatorManager

    companion object {
        fun newInstance() = OverviewTwoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_overview_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button3.setOnClickListener {
            coordinatorManager.navigateInFeature(FlowCoordinator.States.DETAIL)
        }
    }
}
