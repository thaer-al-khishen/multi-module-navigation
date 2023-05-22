package com.thaer.core.domain.usecase

import com.thaer.core.data.repositories.HomeRepository
import com.thaer.core.domain.repository.IHomeRepository
import javax.inject.Inject

class GetMarvelHeroesUseCaseImpl @Inject constructor(private val repository: IHomeRepository): GetMarvelHeroesUseCase {
    override suspend operator fun invoke() = repository.getAllMarvelHeroes()
}
