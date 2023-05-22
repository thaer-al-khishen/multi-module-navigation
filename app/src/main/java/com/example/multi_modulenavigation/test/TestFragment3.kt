package com.example.multi_modulenavigation.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.example.multi_modulenavigation.R
import com.example.multi_modulenavigation.databinding.FragmentTest3Binding
import com.thaer.core.binding_utils.BaseBindingViewModelFragment

class TestFragment3 : BaseBindingViewModelFragment<FragmentTest3Binding, TestFragment3VM>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.btnToFragment1.setOnClickListener {
//            TestNavigationHandler.getInterface()?.onNavigateToTest1Clicked()
////            TestNavigationHandler.getInterface()?.onNavigateToTest1FromTest3Clicked()
//        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finishAndRemoveTask()
        }
    }
}
