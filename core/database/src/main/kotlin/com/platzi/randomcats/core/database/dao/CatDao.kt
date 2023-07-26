package com.platzi.randomcats.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.platzi.randomcats.core.database.model.CatEntity

@Dao
interface CatDao {

    @Upsert
    suspend fun upsertAll(beers: List<CatEntity>)

    @Query("SELECT * FROM catentity")
    fun pagingSource(): PagingSource<Int, CatEntity>

    @Query("DELETE FROM catentity")
    suspend fun clearAll()
}