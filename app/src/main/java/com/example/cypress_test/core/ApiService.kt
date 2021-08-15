package com.example.cypress_test.core

import android.content.Context
import com.example.cypress_test.core.interceptors.OfflineInterceptor
import com.example.cypress_test.core.interceptors.OnlineInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class ApiService(
    private val context: Context
) {
    private val retrofit: Retrofit

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val offlineInterceptor = OfflineInterceptor(context)
        val onlineInterceptor = OnlineInterceptor()

        val okHttpClient = OkHttpClient.Builder()
            .cache(cache())
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(offlineInterceptor)
            .addNetworkInterceptor(onlineInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    fun <T> createService(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

    private fun cache(): Cache {
        val cacheSize = (10 * 1024 * 1024).toLong()
        return Cache(context.cacheDir, cacheSize)
    }

}