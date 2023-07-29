package com.platzi.feature.catshome.presentation.cats

import com.platzi.feature.catshome.domain.model.CatDetail

sealed class CatsState {
    object Idle: CatsState()
    object Loading: CatsState()
    data class Detail(val detail: CatDetail): CatsState()
    object Error: CatsState()
}
