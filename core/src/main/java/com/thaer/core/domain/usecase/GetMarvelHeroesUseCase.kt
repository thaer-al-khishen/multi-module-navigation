package com.thaer.core.domain.usecase

import com.thaer.core.data.models.Hero

interface GetMarvelHeroesUseCase {
    suspend operator fun invoke(): List<Hero>
}