package com.thaer.core.domain.usecase

import com.thaer.core.data.models.remote.Hero

interface GetMarvelHeroesUseCase {
    suspend operator fun invoke(): List<Hero>
}