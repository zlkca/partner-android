package com.artbird.onsite.network

import com.artbird.onsite.domain.*
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
    .baseUrl(APIConstant.RDB_API_URL)
    .build()

interface ProfileApiService {
    @GET("profile/accounts/{accountId}")
    suspend fun getProfileByAccountId(@Path("accountId") accountId: String): Response<Profile>

    @POST("profiles")
    suspend fun createProfile(@Body profile: Profile): Response<Profile>

    @PUT("profiles/{accountId}")
    suspend fun updateProfileByAccountId(@Path("accountId") accountId: String, @Body updates: Profile): Response<Profile>

    @GET("clients")
    suspend fun searchByRecommender(
        @Query("recommenderId") recommenderId: String,
        @Query("keyword") Keyword : String
    ): List<Client>

    @GET("clients/assignment")
    suspend fun searchByAssignedEmployee(
        @Query("recommenderId") employeeId: String,
        @Query("keyword") Keyword : String
    ): List<Client>

    @GET("clients")
    suspend fun getClientsByRecommenderId(@Query("recommenderId") recommenderId : String): List<Client>





}

object ProfileApi {
    val retrofitService: ProfileApiService by lazy { retrofit.create(ProfileApiService::class.java) }
}