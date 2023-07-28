package com.platzi.feature.catshome.data.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.platzi.feature.catshome.data.interceptor.AuthInterceptor
import com.platzi.feature.catshome.data.moshi.DateMoshiAdapter
import com.platzi.feature.catshome.data.moshi.DefaultIfNullFactory
import com.platzi.feature.catshome.data.remote.CatRemoteMediator
import com.platzi.feature.catshome.data.remote.CatsApi
import com.platzi.randomcats.core.database.di.CatDatabase
import com.platzi.randomcats.core.database.model.CatEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@OptIn(ExperimentalPagingApi::class)
@InstallIn(SingletonComponent::class)
class CatsHomeDataModule {

    @Provides
    @Singleton
    fun provideCatsApi(client: OkHttpClient, moshi: Moshi): CatsApi {
        return Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
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
}