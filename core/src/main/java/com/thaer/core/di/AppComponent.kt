package com.thaer.core.di

//import com.example.multi_modulenavigation.MainActivity
//import com.thaer.dynamicfeature.HomeFragment
//import com.example.multi_modulenavigation.MainActivity
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, ViewModelModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

//    fun inject(activity: MainActivity)
//    fun inject(fragment: HomeFragment)
}
