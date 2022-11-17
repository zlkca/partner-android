package com.artbird.onsite.network

import com.artbird.onsite.domain.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(APIConstant.NONSQL_API_URL)
    .build()

interface RecordApiService {
//    @GET("projects")
//    suspend fun getRecords(): List<Record>

    @GET("projects")
    suspend fun getProjectsByClientId(
        @Query("client.id") clientId : String
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
    val retrofitService: RecordApiService by lazy { retrofit.create(RecordApiService::class.java) }
}