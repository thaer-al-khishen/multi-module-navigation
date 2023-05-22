package com.thaer.core.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import java.lang.reflect.ParameterizedType


interface ViewModelBinding<VM : ViewModel> {
    val viewModel: VM
    fun Fragment.createFragmentWithViewModel()
}

class ViewModelBindingDelegate<VM : ViewModel> : ViewModelBinding<VM> {

    private var _viewModel: VM? = null

    override val viewModel: VM
        get() = requireNotNull(_viewModel) { "The property of viewmodel has been destroyed." }

    override fun Fragment.createFragmentWithViewModel() {
        if (_viewModel == null) {
            val viewModelClass: Class<VM> = getGenericViewModel(this) as Class<VM>
            _viewModel = ViewModelProvider(this).get(viewModelClass)
        }
    }

    private fun getGenericViewModel(genericOwner: Any): Class<ViewModel>? {
        val genericSuperclass = genericOwner.javaClass.genericSuperclass
        val superclass = genericOwner.javaClass.superclass
        if (superclass != null) {
            if (genericSuperclass is ParameterizedType) {
                for (argument in genericSuperclass.actualTypeArguments) {
                    if (((argument) as Class<*>).superclass.equals(ViewModel::class.java)) {
                        return ((argument) as Class<ViewModel>)
                    }
                }
            }
        }
        return null
    }

}
