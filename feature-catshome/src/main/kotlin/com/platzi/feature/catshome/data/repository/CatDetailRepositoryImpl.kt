package com.platzi.feature.catshome.data.repository

import com.platzi.feature.catshome.data.mapper.toCatDetail
import com.platzi.feature.catshome.data.remote.CatsApi
import com.platzi.feature.catshome.domain.model.CatDetail
import com.platzi.feature.catshome.domain.repository.CatDetailRepository

class CatDetailRepositoryImpl(
    private val api: CatsApi,
): CatDetailRepository {
    override suspend fun getCatDetail(catId: String): Result<CatDetail> {
        return try {
            val catDetail = api.getCatDetail(catId)
            Result.success(catDetail.toCatDetail())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
