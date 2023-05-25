package com.thaer.core.network

import com.thaer.core.data.models.remote.Hero
import retrofit2.http.GET

interface ApiService {
    @GET(EndPoints.MARVEL_HEROES)
    suspend fun getAllMarvelHeroes(): List<Hero>
}
