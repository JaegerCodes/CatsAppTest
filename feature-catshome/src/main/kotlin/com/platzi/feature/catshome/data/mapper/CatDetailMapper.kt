package com.platzi.feature.catshome.data.mapper

import com.platzi.feature.catshome.data.remote.dto.CatDetailDto
import com.platzi.feature.catshome.domain.model.Breed
import com.platzi.feature.catshome.domain.model.CatDetail

fun CatDetailDto.toCatDetail() = CatDetail(
    id = id,
    url = url,
    breeds = breeds?.map { breed ->
        Breed(
            id = breed.id,
            name = breed.name,
            origin = breed.origin,
            description = breed.description
        )
    }.orEmpty(),
)
