package com.artbird.onsite.network

import com.artbird.onsite.domain.*
import retrofit2.Response
import retrofit2.http.*


interface AccountApiInterface {
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

    @PUT("password")
    suspend fun changePassword(@Body credential: Credential): Response<Account>

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
    val retrofit = ApiService.getRdbRetrofit()
    val retrofitService: AccountApiInterface by lazy { retrofit.create(AccountApiInterface::class.java) }
//    val retrofitService: AddressApiService by lazy { retrofit.create(AddressApiService::class.java) }
}