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

interface ClientApiService {
    @GET("clients")
    suspend fun searchByRecommender(
        @Query("recommenderId") recommenderId: String,
        @Query("keyword") Keyword : String
    ): List<Client>

    @GET("clients")
    suspend fun getClientsByRecommenderId(@Query("recommenderId") recommenderId : String): List<Client>

    @GET("clients/{id}")
    suspend fun getClientDetails(@Path("id") id: String): Client2

    @POST("clients")
    suspend fun createClient(@Body client: Client2): PostResponse

    @PUT("clients/{id}")
    suspend fun updateClient(@Path("id") id: String, @Body updates: Client2): PutResponse

}

object ClientApi {
    val retrofitService: ClientApiService by lazy { retrofit.create(ClientApiService::class.java) }
}