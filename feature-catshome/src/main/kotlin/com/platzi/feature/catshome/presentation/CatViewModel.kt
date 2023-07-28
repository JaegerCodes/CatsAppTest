package com.platzi.feature.catshome.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.platzi.feature.catshome.data.mapper.toCat
import com.platzi.randomcats.core.database.model.CatEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    pager: Pager<Int, CatEntity>
): ViewModel() {

    val catPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toCat() }
        }
        .cachedIn(viewModelScope)
}