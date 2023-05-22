package com.thaer.core.fragment

import androidx.fragment.app.Fragment
//import androidx.fragment.app.activityViewModels
import com.thaer.core.viewmodel.MainViewModel

open class BaseBottomNavFragment: Fragment() {

//    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onResume() {
        super.onResume()
//        mainViewModel.showBottomNav()
    }
}