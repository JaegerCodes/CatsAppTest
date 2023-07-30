package com.platzi.feature.catshome.data.remote.dto

import com.squareup.moshi.Json

data class CatDetailDto(
    @Json(name = "id") val id: String,
    @Json(name = "url") val url: String,
    @Json(name = "breeds") val breeds: List<BreedDto>?,
    @Json(name = "width") val width: Int,
    @Json(name = "height") val height: Int
)
