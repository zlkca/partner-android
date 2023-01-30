package com.artbird.onsite.network

import com.artbird.onsite.domain.DeleteResponse
import com.artbird.onsite.domain.PostResponse
import com.artbird.onsite.domain.PutResponse
import com.artbird.onsite.domain.Window
import retrofit2.Response
import retrofit2.http.*


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
    val retrofit = ApiService.getNonsqlRetrofit()
    val retrofitService: WindowApiService by lazy { retrofit.create(WindowApiService::class.java) }
}