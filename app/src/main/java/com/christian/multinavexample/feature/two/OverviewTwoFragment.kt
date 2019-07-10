package com.christian.multinavexample.feature.two

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.christian.multinavexample.R
import com.christian.multinavexample.feature.two.nav.TwoFlowCoordinator
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import com.christian.multinavlib.ui.SubFeatureFragment
import kotlinx.android.synthetic.main.fragment_overview_two.*


class OverviewTwoFragment : SubFeatureFragment() {

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
            coordinatorManager.navigateInFeature(TwoFlowCoordinator.States.DETAIL, CoordinatorManager.NavigationData(
                params = setupDetailParams()
            ))
        }
    }

    private fun setupDetailParams(): HashMap<String, Any> {
        val params =  HashMap<String, Any>()
        params["test"] = "param"
        return params
    }
}
