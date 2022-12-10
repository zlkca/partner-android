package com.artbird.onsite.network

import com.artbird.onsite.domain.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(APIConstant.RDB_API_URL)
    .build()

interface AccountApiService {
    @POST("search/accounts")
    suspend fun search(
        @Body query: Map<String, String> = mapOf<String, String>()
    ): Response<List<Account>>

    @GET("accounts")
    suspend fun getClientsByRecommenderId(
        @Query("recommenderId") recommenderId : String,
    ): Response<List<Account>>

    @GET("accounts")
    suspend fun getAccountsByEmployeeId(
        @Query("employeeId") employeeId : String,
        @Query("roleName") roleName : String,
    ): Response<List<Account>>

    @POST("login")
    suspend fun login(@Body credential: Credential): Response<Auth>

    @POST("signup")
    suspend fun signup(@Body account: Account): Response<Auth>

    @PUT("password")
    suspend fun changePassword(@Body credential: Credential): PutResponse

//    @GET("clients/assignment")
//    suspend fun searchByAssignedEmployee(
//        @Query("recommenderId") employeeId: String,
//        @Query("keyword") Keyword : String
//    ): List<Account>
//
//    @GET("clients")
//    suspend fun getAccountsByRecommenderId(@Query("recommenderId") recommenderId : String): List<Account>
//
//    @GET("clients/{id}")
//    suspend fun getAccountDetails(@Path("id") id: String): Account2
//
//    @POST("clients")
//    suspend fun createAccount(@Body client: Account2): PostResponse
//
//    @PUT("clients/{id}")
//    suspend fun updateAccount(@Path("id") id: String, @Body updates: Account2): PutResponse

}

object AccountApi {
    val retrofitService: AccountApiService by lazy { retrofit.create(AccountApiService::class.java) }
}