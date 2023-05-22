package com.thaer.core.domain.usecase.either

import com.thaer.core.data.models.Hero
import com.thaer.core.data.repositories.GenericReturnType

interface GetMarvelHeroesEitherUseCase {
    suspend operator fun invoke(): GenericReturnType<List<Hero>>
}