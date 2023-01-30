package com.artbird.onsite.network

import com.artbird.onsite.SharedPreferencesHelper
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


object ApiService {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val cache: MutableMap<String, Any> = mutableMapOf()

    fun put(key: String, value: Any) {
        cache[key] = value
    }

    fun get(key: String): Any? {
        return cache[key]
    }


    fun getRdbRetrofit(): Retrofit {
        val token = cache.get("JWT_TOKEN");

        val authInterceptor = Interceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(newRequest)
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .readTimeout(60000, TimeUnit.SECONDS)
            .connectTimeout(60000, TimeUnit.SECONDS)
            .writeTimeout(60000, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(APIConstant.RDB_API_URL)
            .client(httpClient)
            .build()
    }

    fun getNonsqlRetrofit(): Retrofit {
        val token = cache.get("JWT_TOKEN");

        val authInterceptor = Interceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(newRequest)
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .readTimeout(60000, TimeUnit.SECONDS)
            .connectTimeout(60000, TimeUnit.SECONDS)
            .writeTimeout(60000, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(APIConstant.NONSQL_API_URL)
            .client(httpClient)
            .build()
    }
}


