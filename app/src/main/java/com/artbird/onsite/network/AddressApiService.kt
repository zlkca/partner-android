package com.artbird.onsite.network

import com.artbird.onsite.domain.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
    val retrofitService: AddressApiService by lazy { retrofit.create(AddressApiService::class.java) }
}