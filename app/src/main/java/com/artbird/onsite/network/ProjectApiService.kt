package com.artbird.onsite.network

import com.artbird.onsite.domain.*
import retrofit2.Response
import retrofit2.http.*



interface RecordApiService {
//    @GET("projects")
//    suspend fun getRecords(): List<Record>

    @GET("projects")
    suspend fun getProjectsByClientId(
        @Query("clientId") clientId : String
    ): Response<List<Project>>

    @GET("projects")
    suspend fun getProjectsByRecommenderId(
        @Query("recommender.id") recommenderId : String
    ): Response<List<Project>>

    @GET("projects/{id}")
    suspend fun getRecord(@Path("id") id: String): Response<Project>

    @DELETE("projects/{id}")
    suspend fun deleteRecord(@Path("id") id: String): DeleteResponse?

    @POST("projects")
    suspend fun createRecord(@Body user: Project): PostResponse

    @PUT("projects/{id}")
    suspend fun updateRecord(@Path("id") id: String, @Body updates: Project): PutResponse
}

object RecordApi {
    val retrofit = ApiService.getNonsqlRetrofit()
    val retrofitService: RecordApiService by lazy { retrofit.create(RecordApiService::class.java) }
}