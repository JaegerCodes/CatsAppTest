package com.platzi.randomcats.core.network.di

import com.platzi.randomcats.core.network.interceptor.AuthInterceptor
import com.platzi.randomcats.core.network.moshi.DateMoshiAdapter
import com.platzi.randomcats.core.network.moshi.DefaultIfNullFactory
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
object NetworkModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(DateMoshiAdapter())
        .add(DefaultIfNullFactory())
        .add(KotlinJsonAdapterFactory())
        .build()
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(AuthInterceptor("live_mtICrfeaEKViMcZiRxSaFxFAZk9jxri1FB71pALNy1USFrR97qmDjLMTReFiEG0y"))
            .build()
    }
}
