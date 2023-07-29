package com.platzi.feature.catshome.data.remote

import com.platzi.feature.catshome.data.remote.dto.CatDetailDto
import com.platzi.feature.catshome.data.remote.dto.CatsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CatsApi {
    @GET("images/search")
    suspend fun getCats(
        @Query("limit") limit: Int = 20,
        @Query("page") page: Int,
    ): List<CatsDto>

    @GET("images/{imageId}")
    suspend fun getCatDetail(@Path("imageId") imageId: String): CatDetailDto
}