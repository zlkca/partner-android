package com.artbird.onsite.network

import com.artbird.onsite.domain.*
import retrofit2.Response
import retrofit2.http.*


interface ProfileApiInterface {
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
    val retrofit = ApiService.getRdbRetrofit()
    val retrofitService: ProfileApiInterface by lazy { retrofit.create(ProfileApiInterface::class.java) }
}