package com.thaer.core.binding_utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
//import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.thaer.core.viewmodel.MainViewModel
import com.thaer.core.viewmodel.ViewModelBinding
import com.thaer.core.viewmodel.ViewModelBindingDelegate
import kotlinx.coroutines.launch

abstract class BaseBindingViewModelFragment<VB : ViewBinding, VM: ViewModel> : Fragment(),
    FragmentBinding<VB> by FragmentBindingDelegate(), ViewModelBinding<VM> by ViewModelBindingDelegate() {

//    protected val mainViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        createViewWithBinding(inflater, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createFragmentWithViewModel()
    }

    inline fun Fragment.launchFragmentLifecycleScope(crossinline action: suspend () -> Unit) {
        this.viewLifecycleOwner.lifecycleScope.launch {
            action.invoke()
        }
    }

    inline fun Fragment.launchWhenCreated(crossinline action: suspend () -> Unit) {
        this.viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            action.invoke()
        }
    }

    inline fun Fragment.launchWhenStarted(crossinline action: suspend () -> Unit) {
        this.viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            action.invoke()
        }
    }

    inline fun Fragment.launchWhenResumed(crossinline action: suspend () -> Unit) {
        this.viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            action.invoke()
        }
    }

}
