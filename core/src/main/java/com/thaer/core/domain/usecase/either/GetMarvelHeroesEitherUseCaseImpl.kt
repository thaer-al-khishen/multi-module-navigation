package com.thaer.core.domain.usecase.either

import com.thaer.core.data.models.Hero
import com.thaer.core.data.repositories.GenericReturnType
import com.thaer.core.domain.repository.IHomeRepository
import javax.inject.Inject

class GetMarvelHeroesEitherUseCaseImpl @Inject constructor(private val repository: IHomeRepository): GetMarvelHeroesEitherUseCase {
    override suspend fun invoke(): GenericReturnType<List<Hero>> = repository.getAllMarvelHeroesAsEither()
}