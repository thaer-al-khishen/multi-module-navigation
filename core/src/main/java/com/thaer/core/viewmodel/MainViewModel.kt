package com.thaer.core.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

//@HiltViewModel
class MainViewModel : ViewModel() {

    private val _showLoader: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showLoader: StateFlow<Boolean> = _showLoader
//    val showBottomNav: StateFlow<Boolean> = _showButtomNav

    fun showBottomNav() {
        _showLoader.value = true
    }

    fun hideBottomNav() {
        _showLoader.value = false
    }

}
