package com.example.multi_modulenavigation

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.thaer.core.fragment_input_data_utils.DataInputClass

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

fun NavController.navigateOnce(navDirections: NavDirections) {
    val navOptions = NavOptions.Builder()
        .setLaunchSingleTop(true)  // Use singleTop to avoid duplicate entries in the back stack
        .build()

    this.navigate(
        navDirections,
        navOptions
    )
}

fun NavController.navigateOnce(fragmentId: Int, bundle: Bundle? = null) {
    val navOptions = NavOptions.Builder()
        .setLaunchSingleTop(true)  // Use singleTop to avoid duplicate entries in the back stack
        .build()

    this.navigate(
        fragmentId,
        bundle,
        navOptions
    )
}

fun NavController.navigateOnceWithDataInputClass(fragmentId: Int, dataInputClass: DataInputClass) {
    val navOptions = NavOptions.Builder()
        .setLaunchSingleTop(true)  // Use singleTop to avoid duplicate entries in the back stack
        .build()

    val destinationBundle = bundleOf(dataInputClass.getNavKey() to dataInputClass)

    this.navigate(
        fragmentId,
        destinationBundle,
        navOptions
    )
}

fun NavController.navigateOnceWithDataInputClass(navDirections: NavDirections, dataInputClass: DataInputClass) {
    val navOptions = NavOptions.Builder()
        .setLaunchSingleTop(true)  // Use singleTop to avoid duplicate entries in the back stack
        .build()

    val destinationBundle = Bundle().apply {
        putSerializable(dataInputClass.getNavKey(), dataInputClass)
    }

    val actionId = navDirections.actionId
    val mergedBundle = Bundle().apply {
        putAll(navDirections.arguments)
        putAll(destinationBundle)
    }

    this.navigate(actionId, mergedBundle, navOptions)
}


fun NavController.navigateWithMessageAndClearBackStack(fragmentId: Int, message: String?) {

    val navOptions = NavOptions.Builder()
        .setLaunchSingleTop(true)
        .setPopUpTo(R.id.nav_graph, true)
        .build()

    val bundle = Bundle().apply {
        putString("message", message)
        putBoolean("showMessage", true)
    }

    this.navigate(fragmentId, bundle, navOptions)
}

fun NavController.safeNavigateWith(fragmentId: Int, navDirections: () -> NavDirections) {
    if (this.currentDestination?.id == fragmentId) {
        this.navigateOnce(navDirections())
    }
}

fun NavController.safeNavigateWith(fragmentId: Int, dataInputClass: DataInputClass, navDirections: () -> NavDirections) {
    if (this.currentDestination?.id == fragmentId) {
        this.navigateOnceWithDataInputClass(navDirections(), dataInputClass)
    }
}
