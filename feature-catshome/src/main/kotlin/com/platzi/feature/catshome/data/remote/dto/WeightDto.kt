package com.platzi.feature.catshome.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeightDto(
    @Json(name = "imperial") val imperial: String?,
    @Json(name = "metric") val metric: String?
)