package com.thaer.core.network

import arrow.core.Either
import com.thaer.core.data.models.Hero

interface ApiHelper {
    suspend fun getAllMarvelHeroes(): List<Hero>
}