package com.thaer.core.network

import com.thaer.core.data.models.remote.Hero

interface ApiHelper {
    suspend fun getAllMarvelHeroes(): List<Hero>
}