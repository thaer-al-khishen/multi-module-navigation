package com.thaer.core

import android.app.Application
import android.content.Context
import com.thaer.core.data.room.AppDatabase
import com.thaer.core.di.AppComponent
import com.thaer.core.di.CoreComponent
import com.thaer.core.di.DaggerAppComponent
import com.thaer.core.di.DaggerCoreComponent

open class MainApplication: Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: MainApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        AppDatabase.getDatabase(this)
    }

    val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }

}