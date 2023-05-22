package com.thaer.home.di

import androidx.lifecycle.ViewModel
import com.thaer.core.di.ViewModelKey
import com.thaer.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class HomeViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindDetailMovieViewModel(viewModel: HomeViewModel): ViewModel

}
