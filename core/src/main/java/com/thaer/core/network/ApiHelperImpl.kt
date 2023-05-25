package com.thaer.core.network

import com.thaer.core.data.models.remote.Hero
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(val apiService: ApiService): ApiHelper {
    override suspend fun getAllMarvelHeroes(): List<Hero> = apiService.getAllMarvelHeroes()
}