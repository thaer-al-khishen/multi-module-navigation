package com.thaer.core.di

import android.content.Context
import com.thaer.core.domain.repository.IHomeRepository
import com.thaer.core.domain.prefs.Prefs
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class, SharedPrefModule::class]
)
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun provideRepository() : IHomeRepository
    fun providePrefs() : Prefs

}