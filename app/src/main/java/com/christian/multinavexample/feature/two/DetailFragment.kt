package com.christian.multinavexample.feature.two

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.christian.multinavexample.R
import com.christian.multinavlib.ui.SubFeatureFragment
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : SubFeatureFragment() {

    companion object {
        fun newInstance(paramId: String? = null) = DetailFragment().apply {
            arguments = Bundle().apply {
                putString("test", paramId)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        testText.text = arguments?.get("test").toString()
    }
}
