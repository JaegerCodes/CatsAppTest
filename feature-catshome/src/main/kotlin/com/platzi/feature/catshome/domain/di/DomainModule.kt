package com.platzi.feature.catshome.domain.di

import androidx.paging.Pager
import com.platzi.feature.catshome.domain.repository.CatDetailRepository
import com.platzi.feature.catshome.domain.usecase.CatsUseCases
import com.platzi.feature.catshome.domain.usecase.GetCatDetail
import com.platzi.feature.catshome.domain.usecase.GetPagerCats
import com.platzi.randomcats.core.database.model.CatEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @ViewModelScoped
    @Provides
    fun provideCatsUseCases(
        repository: CatDetailRepository,
        pager: Pager<Int, CatEntity>
    ): CatsUseCases {
        return CatsUseCases(
            getCatDetail = GetCatDetail(repository),
            getPagerCats = GetPagerCats(pager)
        )
    }
}