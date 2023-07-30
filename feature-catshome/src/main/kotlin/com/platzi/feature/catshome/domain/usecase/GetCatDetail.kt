package com.platzi.feature.catshome.domain.usecase

import com.platzi.feature.catshome.domain.model.CatDetail
import com.platzi.feature.catshome.domain.repository.CatDetailRepository
import kotlinx.coroutines.flow.flow

class GetCatDetail(
    private val repository: CatDetailRepository
) {
    suspend operator fun invoke(
        catId: String,
    ) = flow {
        repository.getCatDetail(catId).fold(
            onSuccess = {
                data -> emit(Result.Success(data))
            },
            onFailure = {
                emit(Result.Error)
            }
        )
    }

    sealed class Result {
        data class Success(val detail: CatDetail): Result()
        object Error: Result()
    }
}
