package com.example.multi_modulenavigation.test

import com.google.gson.JsonObject
import com.thaer.core.navigation.BaseNavigationController
import com.thaer.home.HomeNavigationHandler

object TestNavigationHandler: BaseNavigationController<TestNavigationHandler.ITestNavigation>() {
    interface ITestNavigation {
//        fun onNavigateToTest1FromTest3Clicked()
        fun onNavigateToTest1Clicked()
        fun onNavigateToTest2Clicked()
        fun onNavigateToTest3Clicked()
        fun onNavigateToHomeClicked(jsonObject: JsonObject)
    }
}
