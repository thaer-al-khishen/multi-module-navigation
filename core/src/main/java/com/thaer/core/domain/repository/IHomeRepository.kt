package com.thaer.core.domain.repository

import com.thaer.core.data.models.local.User
import com.thaer.core.data.models.remote.Hero
import com.thaer.core.data.repositories.GenericReturnType

interface IHomeRepository {
    suspend fun getAllMarvelHeroes(): List<Hero>
    suspend fun getAllUsers(): List<User>
    suspend fun getAllMarvelHeroesAsEither(): GenericReturnType<List<Hero>>
}