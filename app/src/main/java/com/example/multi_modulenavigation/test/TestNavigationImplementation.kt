package com.example.multi_modulenavigation.test

import androidx.navigation.NavController
import com.example.multi_modulenavigation.R
import com.example.multi_modulenavigation.navigateOnce
import com.example.multi_modulenavigation.navigateWithMessageAndClearBackStack
import com.example.multi_modulenavigation.safeNavigateWith
import com.google.android.material.bottomnavigation.BottomNavigationView

class TestNavigationImplementation(
    private val navController: NavController,
    private val bottomNavigationView: BottomNavigationView
) {

//    private var isNavigationProgrammatic = false

    init {
        TestNavigationHandler.setInterface(object : TestNavigationHandler.ITestNavigation {

            //If you have the scenario mentioned at the bottom
//            override fun onNavigateToTest1FromTest3Clicked() {
//                isNavigationProgrammatic = true
//                bottomNavigationView.selectedItemId = R.id.testFragment1
//            }

            override fun onNavigateToTest1Clicked() {
                if (navController.currentDestination?.id == R.id.testFragment3) {
                    navController.navigateOnce(R.id.testFragment1)
                }
            }

            override fun onNavigateToTest2Clicked() {
                navController.safeNavigateWith(R.id.testFragment1) {
                    TestFragment1Directions.actionTestFragment1ToTestFragment2()
                }
            }

            override fun onNavigateToTest3Clicked() {
                if (navController.currentDestination?.id == R.id.testFragment2) {
                    navController.navigateOnce(TestFragment2Directions.actionTestFragment2ToTestFragment3())
                }
            }

            override fun onNavigateToHomeClicked() {
//                if (navController.currentDestination?.id == R.id.testFragment1) {
//                    navController.navigateOnce(TestFragment1Directions.actionTestFragment1ToHomeFragment())
//                }
//                if (navController.currentDestination?.id == R.id.testFragment2) {
//                    navController.navigateOnce(R.id.homeFragment)
//                }
                navController.navigateOnce(R.id.homeFragment)
//                navController.navigateWithMessageAndClearBackStack(R.id.testFragment1, "Popped back to fragment 1!")
            }

        })


    }

}

//When you have the very rare case of a bottom navigating between bottom nav fragments, or an error message taking you to a bottom nav fragment:
//    private fun setupOnDestinationChangedListener() {
//        bottomNavigationView.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.testFragment1 -> {
//                    if (isNavigationProgrammatic) {
//                        navController.navigateWithMessageAndClearBackStack(R.id.testFragment1, "Popped back to fragment 1!")
//                        isNavigationProgrammatic = false
//                    } else {
//                        navController.navigateOnce(R.id.testFragment1)
//                    }
//                    true
//                }
//                R.id.testFragment2 -> {
//                    navController.navigateOnce(R.id.testFragment2)
//                    true
//                }
//                R.id.testFragment3 -> {
//                    navController.navigateOnce(R.id.testFragment3)
//                    true
//                }
//                else -> false
//            }
//        }
