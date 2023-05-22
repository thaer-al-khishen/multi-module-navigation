package com.thaer.core.binding_utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.thaer.core.factory.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseBindingFragment<VB : ViewBinding>: Fragment(), FragmentBinding<VB> by FragmentBindingDelegate() {

//    protected val mainViewModel by activityViewModels<MainViewModel>()b
    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        createViewWithBinding(inflater, container)

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
