package com.platzi.feature.catshome.data.di

import androidx.paging.ExperimentalPagingApi
import com.platzi.feature.catshome.data.remote.CatsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
class CatsHomeDataModule {
    @Provides
    @Singleton
    fun provideCatsApi(client: OkHttpClient): CatsApi {
        return Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create(CatsApi::class.java)
    }

}