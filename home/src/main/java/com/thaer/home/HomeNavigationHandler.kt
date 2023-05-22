package com.thaer.home

import com.thaer.core.navigation.BaseNavigationController

object HomeNavigationHandler: BaseNavigationController<HomeNavigationHandler.IHomeNavigation>() {
    interface IHomeNavigation {
        fun onButtonClicked()
    }
}