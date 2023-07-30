package com.platzi.feature.catshome.presentation.home.detail

import com.platzi.feature.catshome.domain.model.CatDetail

sealed class CatDetailState {
    object Idle: CatDetailState()
    object Loading: CatDetailState()
    data class Detail(val cat: CatDetail): CatDetailState()
}
