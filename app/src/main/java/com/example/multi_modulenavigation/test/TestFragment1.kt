package com.example.multi_modulenavigation.test

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import com.example.multi_modulenavigation.databinding.FragmentTest1Binding
import com.thaer.core.binding_utils.BaseBindingViewModelFragment

class TestFragment1 : BaseBindingViewModelFragment<FragmentTest1Binding, TestFragment1VM>() {

    private var showMessage: Boolean = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Don't use this with bottom nav fragments, show this toast from the main activity when possible
        //And if you need tp update something in this fragment, intercept the message in the main activity, update value in sharedviewmodel
        arguments?.let { bundle ->
            bundle.getString("message")?.let { errorMessage ->
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

//        binding.btnToFragment2.setOnClickListener {
//            TestNavigationHandler.getInterface()?.onNavigateToTest2Clicked()
//        }

        binding.btnToHomeFragment.setOnClickListener {
            TestNavigationHandler.getInterface()?.onNavigateToHomeClicked()
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finishAndRemoveTask()
        }

    }

}
