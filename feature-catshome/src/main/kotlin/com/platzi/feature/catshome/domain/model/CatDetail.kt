package com.platzi.feature.catshome.domain.model

data class CatDetail(
    val id: String,
    val url: String,
    val breeds: List<Breed>,
)