package com.platzi.randomcats.core.network.di

import com.platzi.randomcats.core.network.NetworkConfig
import com.platzi.randomcats.core.network.moshi.DateMoshiAdapter
import com.platzi.randomcats.core.network.moshi.DefaultIfNullFactory
import com.platzi.randomcats.core.network.interceptor.AuthInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(DateMoshiAdapter())
        .add(DefaultIfNullFactory())
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        networkConfig: NetworkConfig
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(AuthInterceptor(networkConfig.apiKey))
            .build()
    }
}