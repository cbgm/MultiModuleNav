package com.christian.multinavexample.feature.two

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.christian.multinavexample.R
import com.christian.multinavexample.feature.two.nav.FlowCoordinator
import com.christian.multinavlib.ui.SubFeatureFragment
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : SubFeatureFragment() {
    companion object {
        fun newInstance() = MenuFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button1.setOnClickListener {
            coordinatorManager.navigateInFeature(FlowCoordinator.States.OVERVIEW)
        }
        button2.setOnClickListener {
            coordinatorManager.navigateInFeature(FlowCoordinator.States.OVERVIEW2)
        }
    }
}
