package com.artbird.onsite.network

import com.artbird.onsite.domain.Account
import com.artbird.onsite.domain.Auth
import com.artbird.onsite.domain.Credential
import com.artbird.onsite.domain.PutResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
//import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

//import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(APIConstant.RDB_API_URL)
    .build()

interface AuthApiService {
    @POST("login")
    suspend fun login(@Body credential: Credential): Response<Auth>

    @POST("signup")
    suspend fun signup(@Body account: Account): Response<Auth>

    @PUT("password")
    suspend fun changePassword(@Body credential: Credential): PutResponse
}

object AuthApi {
    val retrofitService: AuthApiService by lazy {
        retrofit.create(AuthApiService::class.java)
    }
}
