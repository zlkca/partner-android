package com.artbird.onsite.network

import com.artbird.onsite.domain.PostResponse
import com.artbird.onsite.domain.PutResponse
import com.artbird.onsite.domain.Role
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

interface RoleApiService {
    @GET("roles")
    suspend fun getRoles(): List<Role>

    @GET("roles")
    suspend fun getRolesByName(@Query("name") name : String): List<Role>

    @GET("roles/{id}")
    suspend fun getRole(@Path("id") id: String): Role

    @POST("roles")
    suspend fun createRole(@Body role: Role): PostResponse

    @PUT("roles/{id}")
    suspend fun updateRole(@Path("id") id: String, @Body updates: Role): PutResponse
}

object RoleApi {
    val retrofitService: RoleApiService by lazy { retrofit.create(RoleApiService::class.java) }
}