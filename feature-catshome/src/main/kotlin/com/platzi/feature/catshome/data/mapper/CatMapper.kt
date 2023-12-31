package com.platzi.feature.catshome.data.mapper

import com.platzi.feature.catshome.data.remote.dto.CatsDto
import com.platzi.feature.catshome.domain.model.Cat
import com.platzi.randomcats.core.database.model.CatEntity

fun CatsDto.toCatEntity(): CatEntity {
    return CatEntity(
        id = id,
        url = url,
        width = width,
        height = height,
    )
}

fun CatEntity.toCat(): Cat {
    return Cat(
        index = index,
        id = id,
        url = url,
        width = width,
        height = height,
    )
}
