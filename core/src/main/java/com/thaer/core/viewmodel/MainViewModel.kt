package com.thaer.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thaer.core.utils.OneTimeEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

//@HiltViewModel
class MainViewModel : ViewModel() {

    private val _showLoader: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showLoader: StateFlow<Boolean> = _showLoader
//    val showBottomNav: StateFlow<Boolean> = _showButtomNav

    private val _sharedData = MutableLiveData<OneTimeEvent<SharedData>>()
    val sharedData: LiveData<OneTimeEvent<SharedData>> = _sharedData

    fun configureSharedData(sharedDataInput: SharedData) {
        _sharedData.value = OneTimeEvent(sharedDataInput)
    }

    fun showBottomNav() {
        _showLoader.value = true
    }

    fun hideBottomNav() {
        _showLoader.value = false
    }

    sealed class SharedData {
        data class NavigationSharedMessage(val message: String): SharedData()
    }

}

