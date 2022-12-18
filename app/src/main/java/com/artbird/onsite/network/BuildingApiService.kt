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
    .baseUrl(APIConstant.NONSQL_API_URL)
    .build()

interface BuildingApiService {
    @GET("buildings")
    suspend fun getBuildings(): List<Building>

    @POST("buildings")
    suspend fun createBuilding(@Body building: Building): Building

    @POST("buildings/sample")
    suspend fun createSampleBuilding(@Body req: SampleBuildingReqBody): Response<Building>

    @PUT("buildings/{id}")
    suspend fun updateBuilding(@Path("id") id: String, @Body updates: Building): Building?

    @GET("buildings/{id}")
    suspend fun getBuilding(@Path("id") id: String): Building?

    @GET("buildings/appointments/{appointmentId}")
    suspend fun getBuildingsByAppointmentId(@Path("appointmentId") appointmentId: String): List<Building>?

    @DELETE("buildings/{id}")
    suspend fun deleteBuilding(
        @Path("id") id: String,
        @Query("appointmentId") appointmentId: String,
    ): List<Building>?

    @DELETE("floors/{id}")
    suspend fun deleteFloor(
        @Path("id") id: String,
        @Query("buildingId") buildingId: String,
    ): Building?

    @DELETE("rooms/{id}")
    suspend fun deleteRoom(
        @Path("id") id: String,
        @Query("buildingId") buildingId : String,
        @Query("floorId") floorId: String,
    ): Building?

}

object BuildingApi {
    val retrofitService: BuildingApiService by lazy { retrofit.create(BuildingApiService::class.java) }
}