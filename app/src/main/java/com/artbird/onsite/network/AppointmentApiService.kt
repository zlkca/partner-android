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

interface AppointmentApiService {
    @GET("appointments")
    suspend fun getAppointmentsByEmployeeId(@Query("employee.id") employeeId : String): Response<List<Appointment>>

    @GET("appointments/{id}")
    suspend fun getAppointment(@Path("id") id: String): Response<Appointment>

    @POST("appointments")
    suspend fun createAppointment(@Body user: Appointment): Response<Appointment>

    @PUT("appointments/{id}")
    suspend fun updateAppointment(@Path("id") id: String, @Body updates: Appointment): Response<Appointment>
}

object AppointmentApi {
    val retrofitService: AppointmentApiService by lazy { retrofit.create(AppointmentApiService::class.java) }
}