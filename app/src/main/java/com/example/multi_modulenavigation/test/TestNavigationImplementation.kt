package com.example.multi_modulenavigation.test

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.multi_modulenavigation.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.thaer.home.HomeFragmentInput
import com.thaer.home.HomeInputSource

class TestNavigationImplementation(
    private val navController: NavController,
    private val bottomNavigationView: BottomNavigationView
) {

    private var isNavigationProgrammatic = false

    init {
        TestNavigationHandler.setInterface(object : TestNavigationHandler.ITestNavigation {

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
                val homeFragmentInput = HomeFragmentInput("Thaer", "Generic function", "", HomeInputSource.FROM_TEST_1)
                navController.navigateOnceWithDataInputClass(R.id.homeFragment, homeFragmentInput)
//                val bundle = bundleOf(HomeFragmentInput().getNavKey() to homeFragmentInput)
//                navController.navigateOnce(R.id.homeFragment, bundle)
//                isNavigationProgrammatic = true
//                bottomNavigationView.selectedItemId = R.id.testFragment1
//                navController.navigateWithMessageAndClearBackStack(R.id.testFragment1, "Popped back to fragment 1!")
            }

        })

    }

}

//When you have the very rare case of a bottom navigating between bottom nav fragments, or an error message taking you to a bottom nav fragment:
//    private fun setupOnDestinationChangedListener(bottomNavigationView: BottomNavigationView) {
//    bottomNavigationView.setOnItemSelectedListener { item ->
//        when (item.itemId) {
//            R.id.testFragment1 -> {
//                if (isNavigationProgrammatic) {
//                    navController.navigateWithMessageAndClearBackStack(
//                        R.id.testFragment1,
//                        "Popped back to fragment 1!"
//                    )
//                    isNavigationProgrammatic = false
//                } else {
//                    navController.navigateOnce(R.id.testFragment1)
//                }
//                true
//            }
//            R.id.testFragment2 -> {
//                navController.navigateOnce(R.id.testFragment2)
//                true
//            }
//            R.id.testFragment3 -> {
//                navController.navigateOnce(R.id.testFragment3)
//                true
//            }
//            else -> false
//        }
//    }
//}
