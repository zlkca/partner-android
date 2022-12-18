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
    .baseUrl(APIConstant.NONSQL_API_URL)
    .build()

interface AppointmentApiService {
    @GET("appointments")
    suspend fun getAppointmentsByEmployeeId(@Query("employee.id") employeeId : String): List<Appointment2>

    @GET("appointments/{id}")
    suspend fun getAppointment(
        @Path("id") id: String
    ): Appointment2

    @POST("appointments")
    suspend fun createAppointment(@Body user: Appointment2): PostResponse

    @PUT("appointments/{id}")
    suspend fun updateAppointment(@Path("id") id: String, @Body updates: Appointment2): PutResponse
}

object AppointmentApi {
    val retrofitService: AppointmentApiService by lazy { retrofit.create(AppointmentApiService::class.java) }
}