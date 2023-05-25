package com.thaer.core.binding_utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.thaer.core.data.room.UserDao
import com.thaer.core.domain.prefs.Prefs
import com.thaer.core.factory.ViewModelFactory
import com.thaer.core.fragment_input_data_utils.DataInputClass
import com.thaer.core.fragment_input_data_utils.DataInputClassBinding
import com.thaer.core.fragment_input_data_utils.DataInputClassBindingDelegate
import kotlinx.coroutines.launch
import javax.inject.Inject

open class BaseBindingDataInputFragment<VB : ViewBinding, DIC: DataInputClass>: Fragment(),
    FragmentBinding<VB> by FragmentBindingDelegate(), DataInputClassBinding<DIC> by DataInputClassBindingDelegate() {

    @Inject
    lateinit var factory: ViewModelFactory

    @Inject
    lateinit var prefs: Prefs

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        createViewWithBinding(inflater, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createDataInputClass()
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
