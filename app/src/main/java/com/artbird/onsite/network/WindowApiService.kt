package com.artbird.onsite.network

import com.artbird.onsite.domain.DeleteResponse
import com.artbird.onsite.domain.PostResponse
import com.artbird.onsite.domain.PutResponse
import com.artbird.onsite.domain.Window
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*



private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(APIConstant.NONSQL_API_URL)
    .build()

interface WindowApiService {


    @GET("windows/rooms/{roomId}")
    suspend fun getWindowsByRoomId(@Path("roomId") roomId: String): Response<List<Window>>

    @GET("windows/{id}")
    suspend fun getWindow(@Path("id") id: String): Response<Window>


    @GET("windows")
    suspend fun getWindows(): List<Window>

    @POST("windows")
    suspend fun createWindow(@Body window: Window): PostResponse


    @PUT("windows/{id}")
    suspend fun updateWindow(@Path("id") id: String, @Body updates: Window): PutResponse

    @DELETE("windows/{id}")
    suspend fun deleteWindow(@Path("id") id: String): DeleteResponse?
}

object WindowApi {
    val retrofitService: WindowApiService by lazy { retrofit.create(WindowApiService::class.java) }
}