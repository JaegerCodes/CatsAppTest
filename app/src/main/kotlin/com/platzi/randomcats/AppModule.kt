package com.platzi.randomcats

import com.platzi.randomcats.core.network.config.NetworkConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNetworkConfig() = object : NetworkConfig {
        override val url: String
            get() = BuildConfig.CATS_URL
        override val version: String
            get() = BuildConfig.CATS_VERSION
        override val apiKey: String
            get() = BuildConfig.CATS_KEY
    }
}