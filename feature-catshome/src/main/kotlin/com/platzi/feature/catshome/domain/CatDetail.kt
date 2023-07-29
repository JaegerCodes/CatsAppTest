package com.platzi.feature.catshome.domain

data class CatDetail(
    val id: String,
    val url: String,
    val breeds: List<Breed>,
)