package com.thaer.core.domain.repository

import com.thaer.core.data.models.Hero
import com.thaer.core.data.repositories.GenericReturnType

interface IHomeRepository {
    suspend fun getAllMarvelHeroes(): List<Hero>
    suspend fun getAllMarvelHeroesAsEither(): GenericReturnType<List<Hero>>
}