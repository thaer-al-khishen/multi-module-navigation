package com.thaer.core.di

import com.thaer.core.domain.usecase.GetMarvelHeroesUseCase
import com.thaer.core.domain.usecase.GetMarvelHeroesUseCaseImpl
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
abstract class AppModule {
    @Binds
    @Named("HomeModule")
    abstract fun provideMovieUseCase(getMarvelHeroesUseCase: GetMarvelHeroesUseCaseImpl): GetMarvelHeroesUseCase
}