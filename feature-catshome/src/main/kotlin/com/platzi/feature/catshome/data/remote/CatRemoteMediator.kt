package com.platzi.feature.catshome.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.platzi.feature.catshome.data.mapper.toCatEntity
import com.platzi.randomcats.core.database.di.CatDatabase
import com.platzi.randomcats.core.database.model.CatEntity
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CatRemoteMediator @Inject constructor(
    private val catDb: CatDatabase,
    private val catApi: CatsApi
) : RemoteMediator<Int, CatEntity>() {


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CatEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null) {
                        1
                    } else {
                        ((lastItem.index?:1) / state.config.pageSize) + 1
                    }
                }
            }

            val cats = catApi.getCats(
                page = loadKey,
                limit = state.config.pageSize
            )

            catDb.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    catDb.catDao.clearAll()
                }
                val beerEntities = cats.map { it.toCatEntity() }
                catDb.catDao.upsertAll(beerEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = cats.isEmpty()
            )
        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}