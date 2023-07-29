package com.platzi.feature.catshome.domain.usecase

import androidx.paging.Pager
import com.platzi.randomcats.core.database.model.CatEntity

class GetPagerCats(
    private val pager: Pager<Int, CatEntity>
) {
    operator fun invoke(): Pager<Int, CatEntity> = pager
}