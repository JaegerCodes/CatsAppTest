package com.platzi.randomcats

import android.app.Application
import com.platzi.randomcats.core.network.NetworkConfig

class PlatziApplication: Application(), NetworkConfig {
    override val baseUrl: String
        get() = BuildConfig.CATS_URL

    override val apiKey: String
        get() = BuildConfig.CATS_API_KEY
}