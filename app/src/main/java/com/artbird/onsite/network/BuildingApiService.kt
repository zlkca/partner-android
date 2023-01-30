package com.artbird.onsite.network

import com.artbird.onsite.domain.*
import retrofit2.Response
import retrofit2.http.*



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
    val retrofit = ApiService.getNonsqlRetrofit()
    val retrofitService: BuildingApiService by lazy { retrofit.create(BuildingApiService::class.java) }
}