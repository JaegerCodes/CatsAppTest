package com.platzi.randomcats.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .header(HEADER_API_KEY, apiKey)
            .build()

        return chain.proceed(newRequest)
    }

    companion object {
        private const val HEADER_API_KEY = "x-api-key"
    }
}