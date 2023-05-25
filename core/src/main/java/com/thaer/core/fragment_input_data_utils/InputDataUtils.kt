package com.thaer.core.fragment_input_data_utils

import android.os.Build
import androidx.fragment.app.Fragment

inline fun <reified T : DataInputClass> Fragment.getInputData(): T? {
    arguments?.let {
        val navKey = T::class.java.newInstance().getNavKey()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return it.getSerializable(navKey, T::class.java)
        } else {
            return it.getSerializable(navKey) as? T
        }
    } ?: run {
        return null
    }
}

fun <T : DataInputClass> Fragment.getInputData(dataClass: T): T? {
    arguments?.let {
        val navKey = dataClass.getNavKey()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return it.getSerializable(navKey, dataClass.javaClass)
        } else {
            return it.getSerializable(navKey) as? T
        }
    } ?: run {
        return null
    }
}
