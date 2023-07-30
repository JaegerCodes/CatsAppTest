package com.platzi.feature.catshome.presentation.home

import com.platzi.feature.catshome.domain.model.CatDetail

sealed class CatsState {

    object Loading: CatsState()

    data class Detail(val cat: CatDetail): CatsState()

    object Error: CatsState()
}
