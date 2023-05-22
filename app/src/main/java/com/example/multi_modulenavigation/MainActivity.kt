package com.example.multi_modulenavigation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.multi_modulenavigation.test.TestNavigationImplementation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.thaer.core.CoreActivity
import com.thaer.core.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : CoreActivity() {

    val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)

        bottomNavigationView.setupWithNavController(navController)

        val bottomNavFragments = setOf(R.id.testFragment1, R.id.testFragment2, R.id.testFragment3)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNavigationView.visibility = if (destination.id in bottomNavFragments) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        HomeNavigationImplementation(navController)
        TestNavigationImplementation(navController, bottomNavigationView)

        lifecycleScope.launchWhenCreated {
            mainViewModel.showLoader.collectLatest {
                if (it) {
                    Log.d("ThaerOutput","Changed")
                }
            }
        }

    }

}
