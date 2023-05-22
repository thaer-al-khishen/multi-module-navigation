package com.thaer.home.di

import com.thaer.core.domain.usecase.GetMarvelHeroesUseCaseImpl
import com.thaer.core.domain.usecase.GetMarvelHeroesUseCase
import com.thaer.core.domain.usecase.either.GetMarvelHeroesEitherUseCase
import com.thaer.core.domain.usecase.either.GetMarvelHeroesEitherUseCaseImpl
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
abstract class HomeModule {
    @Binds
    @Named("HomeModule")
    abstract fun provideMovieUseCase(getMarvelHeroesUseCase: GetMarvelHeroesUseCaseImpl): GetMarvelHeroesUseCase

    @Binds
    @Named("HomeModule")
    abstract fun provideMovieEitherUseCase(getMarvelHeroesEitherUseCase: GetMarvelHeroesEitherUseCaseImpl): GetMarvelHeroesEitherUseCase
}
