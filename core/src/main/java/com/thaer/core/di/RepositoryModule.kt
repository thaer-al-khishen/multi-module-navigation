package com.thaer.core.di

import com.thaer.core.data.repositories.HomeRepository
import com.thaer.core.domain.repository.IHomeRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class])
abstract class RepositoryModule {
    @Binds
    abstract fun provideMovieRepository(repository: HomeRepository): IHomeRepository
}