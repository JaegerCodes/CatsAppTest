package com.platzi.feature.catshome.data.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.platzi.feature.catshome.data.remote.CatRemoteMediator
import com.platzi.feature.catshome.data.remote.CatsApi
import com.platzi.feature.catshome.data.repository.CatDetailRepositoryImpl
import com.platzi.feature.catshome.domain.repository.CatDetailRepository
import com.platzi.randomcats.core.database.di.CatDatabase
import com.platzi.randomcats.core.database.model.CatEntity
import com.platzi.randomcats.core.network.config.NetworkConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@OptIn(ExperimentalPagingApi::class)
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideCatsApi(client: OkHttpClient, moshi: Moshi, networkConfig: NetworkConfig): CatsApi {
        return Retrofit.Builder()
            .baseUrl("${networkConfig.url}/${networkConfig.version}/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(CatsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBeerPager(catDb: CatDatabase, catApi: CatsApi): Pager<Int, CatEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = CatRemoteMediator(
                catDb = catDb,
                catApi = catApi
            ),
            pagingSourceFactory = {
                catDb.catDao.pagingSource()
            }
        )
    }

    @Provides
    @Singleton
    fun provideCatDetailRepository(catApi: CatsApi): CatDetailRepository {
        return CatDetailRepositoryImpl(catApi)
    }
}