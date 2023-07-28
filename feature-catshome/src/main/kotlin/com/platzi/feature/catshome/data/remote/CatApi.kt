package com.platzi.feature.catshome.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface CatsApi {
    @GET("images/search")
    suspend fun getCats(
        @Query("limit") limit: Int = 20,
        @Query("page") page: Int,
    ): List<CatsDto>
}