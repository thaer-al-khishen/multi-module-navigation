package com.thaer.home.di

import com.thaer.core.di.AppScope
import com.thaer.core.di.CoreComponent
import com.thaer.home.HomeFragment
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [HomeModule::class, HomeViewModelModule::class]
)
interface HomeComponent {

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): HomeComponent
    }

    fun inject(fragment: HomeFragment)

}
