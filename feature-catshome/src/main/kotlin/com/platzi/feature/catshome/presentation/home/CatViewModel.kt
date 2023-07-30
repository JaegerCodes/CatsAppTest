package com.platzi.feature.catshome.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.platzi.feature.catshome.data.mapper.toCat
import com.platzi.feature.catshome.domain.usecase.CatsUseCases
import com.platzi.feature.catshome.domain.usecase.GetCatDetail
import com.platzi.feature.catshome.presentation.home.detail.CatDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    private val useCase: CatsUseCases
) : ViewModel() {

    val catPagingFlow = useCase.getPagerCats()
        .flow
        .map { pagingData ->
            pagingData.map { it.toCat() }
        }
        .cachedIn(viewModelScope)

    private val _uiState: MutableStateFlow<CatDetailState> = MutableStateFlow(CatDetailState.Idle)
    val uiState: StateFlow<CatDetailState>
        get() = _uiState
            .asStateFlow()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                CatDetailState.Loading
            )

    fun fetchCatDetail(catId: String) {
        viewModelScope.launch {
            _uiState.value = CatDetailState.Loading
            try {
                useCase.getCatDetail(catId).collect { result ->
                    _uiState.value = when (result) {
                        is GetCatDetail.Result.Error -> CatDetailState.Idle
                        is GetCatDetail.Result.Success -> CatDetailState.Detail(result.detail)
                    }
                }
            } catch (e: Exception) {
                _uiState.value = CatDetailState.Idle
            }
        }
    }
}
