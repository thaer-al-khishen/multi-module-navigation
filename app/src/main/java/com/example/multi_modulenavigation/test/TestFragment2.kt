package com.example.multi_modulenavigation.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.example.multi_modulenavigation.R
import com.example.multi_modulenavigation.databinding.FragmentTest2Binding
import com.thaer.core.binding_utils.BaseBindingViewModelFragment

class TestFragment2 : BaseBindingViewModelFragment<FragmentTest2Binding, TestFragment2VM>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.btnToFragment3.setOnClickListener {
//            TestNavigationHandler.getInterface()?.onNavigateToTest3Clicked()
//        }

        binding.btnToHomeFragment.setOnClickListener {
            TestNavigationHandler.getInterface()?.onNavigateToHomeClicked()
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finishAndRemoveTask()
        }
    }
}
