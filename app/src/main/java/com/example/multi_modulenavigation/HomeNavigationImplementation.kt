package com.example.multi_modulenavigation

import androidx.navigation.NavController
import com.thaer.home.HomeNavigationHandler

class HomeNavigationImplementation(navController: NavController) {
    init {
        HomeNavigationHandler.setInterface(object : HomeNavigationHandler.IHomeNavigation {
            override fun onButtonClicked() {
                navController.navigateOnce(R.id.testFragment1)
            }
        })
    }
}
