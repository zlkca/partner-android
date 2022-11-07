package com.artbird.onsite.network

import com.artbird.onsite.domain.DeleteResponse
import com.artbird.onsite.domain.Case
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
    .baseUrl(APIConstant.NONSQL_API_URL)
    .build()

interface CaseApiService {
    @GET("cases")
    suspend fun getCases(): List<Case>

    @GET("addresses/{addressId}/case")
    suspend fun getCaseByAddressId(@Path("addressId") addressId: String): Case?

    @GET("cases/{id}")
    suspend fun getCase(@Path("id") id: String): Case?

    @DELETE("cases/{id}")
    suspend fun deleteCase(@Path("id") id: String): DeleteResponse?
}

object CaseApi {
    val retrofitService: CaseApiService by lazy { retrofit.create(CaseApiService::class.java) }
}