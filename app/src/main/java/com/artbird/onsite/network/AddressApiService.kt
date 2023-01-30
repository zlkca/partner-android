package com.artbird.onsite.network

import com.artbird.onsite.domain.*
import retrofit2.http.*

// Deprecated
interface AddressApiService {
    @GET("Addresses")
    suspend fun getAddressByClientId(@Query("clientId") clientId : String): List<Address>

    @GET("Addresses/{id}")
    suspend fun getAddress(
        @Path("id") id: String
    ): Address

    @POST("Addresses")
    suspend fun createAddress(@Body address: Address): PostResponse

    @PUT("Addresses/{id}")
    suspend fun updateAddress(@Path("id") id: String, @Body updates: Address): PutResponse
}

object AddressApi {
    val retrofit = ApiService.getNonsqlRetrofit()
    val retrofitService: AddressApiService by lazy { retrofit.create(AddressApiService::class.java) }
}