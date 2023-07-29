package com.platzi.feature.catshome.domain.repository

import com.platzi.feature.catshome.domain.model.CatDetail

interface CatDetailRepository {
    suspend fun getCatDetail(catId: String): Result<CatDetail>
}