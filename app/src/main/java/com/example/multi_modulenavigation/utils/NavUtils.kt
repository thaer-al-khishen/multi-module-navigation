package com.example.multi_modulenavigation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

fun NavController.popBackStackAllInstances(destination: Int, inclusive: Boolean): Boolean {
    var popped: Boolean
    while (true) {
        popped = popBackStack(destination, inclusive)
        if (!popped) {
            break
        }
    }
    return popped
}

fun NavController.navigateOnce(navDirections: NavDirections) {
    val navOptions = NavOptions.Builder()
        .setLaunchSingleTop(true)  // Use singleTop to avoid duplicate entries in the back stack
        .build()

    this.navigate(
        navDirections,
        navOptions
    )
}

fun NavController.navigateOnce(fragmentId: Int) {
    val navOptions = NavOptions.Builder()
        .setLaunchSingleTop(true)  // Use singleTop to avoid duplicate entries in the back stack
        .build()

    this.navigate(
        fragmentId,
        null,
        navOptions
    )
}

fun NavController.navigateWithMessageAndClearBackStack(fragmentId: Int, message: String?) {

    val navOptions = NavOptions.Builder()
        .setLaunchSingleTop(true)
        .setPopUpTo(R.id.nav_graph, true)
        .build()

    val bundle = Bundle().apply {
        putString("message", message)
    }

    this.navigate(fragmentId, bundle, navOptions)
}

fun NavController.navigateAndClearBackStack(fragmentId: Int) {

    val navOptions = NavOptions.Builder()
        .setLaunchSingleTop(true)
        .setPopUpTo(R.id.nav_graph, true)
        .build()

    this.navigate(fragmentId, null, navOptions)
}

fun NavController.navigateAndClearBackStack(navDirections: NavDirections) {

    val navOptions = NavOptions.Builder()
        .setLaunchSingleTop(true)
        .setPopUpTo(R.id.nav_graph, true)
        .build()

    this.navigate(navDirections, navOptions)
}

fun NavController.safeNavigateWith(fragmentId: Int, navDirections: () -> NavDirections) {
    if (this.currentDestination?.id == fragmentId) {
        this.navigateOnce(navDirections())
    }
}

