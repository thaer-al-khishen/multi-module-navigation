package com.thaer.core.navigation

open class BaseNavigationController<T> {

    private var mInterface: T? = null

    fun setInterface(inter: T) {
        mInterface = inter
    }

    fun getInterface(): T? = mInterface

}
