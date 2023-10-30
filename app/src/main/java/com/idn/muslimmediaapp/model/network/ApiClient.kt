package com.idn.muslimmediaapp.model.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    fun provideApiService(): ApiService {
        val okHttpclient = OkHttpClient.Builder()
            .addInterceptor{
                val newRequest = it.request().newBuilder()
                    .addHeader("X-API-KEY", "02708de009a441e0b6a7563786599f85")
                    .build()
                it.proceed(newRequest)
            }
            .readTimeout(10, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .client(okHttpclient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}