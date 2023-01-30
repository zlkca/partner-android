package com.artbird.onsite.network

import com.artbird.onsite.domain.PostResponse
import com.artbird.onsite.domain.PutResponse
import com.artbird.onsite.domain.Role
import retrofit2.http.*




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
    val retrofit = ApiService.getRdbRetrofit()
    val retrofitService: RoleApiService by lazy { retrofit.create(RoleApiService::class.java) }
}