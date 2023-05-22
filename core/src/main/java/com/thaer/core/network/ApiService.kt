package com.thaer.core.network

import arrow.core.Either
import com.thaer.core.data.models.Hero
import retrofit2.http.GET

interface ApiService {
    @GET(EndPoints.MARVEL_HEROES)
    suspend fun getAllMarvelHeroes(): List<Hero>
}
