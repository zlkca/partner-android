package com.artbird.onsite.network

import com.artbird.onsite.domain.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

object APIConstant {
    const val NONSQL_API_URL = "http://192.168.0.13:5001/"
    const val RDB_API_URL = "http://192.168.0.13:5000/"
//    const val GOOGLE_MAP_API_URL = "https://maps.googleapis.com/"
    const val LOCATIONIQ_API_URL = "https://api.locationiq.com/v1/"
// "https://g5kncyxqmb.execute-api.us-east-1.amazonaws.com/dev/"

//    const val NONSQL_API_URL = "https://99sekcen45.execute-api.us-east-1.amazonaws.com/dev/"
//    const val RDB_API_URL = "https://v9n8zwpbrh.execute-api.us-east-1.amazonaws.com/dev/"
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(APIConstant.NONSQL_API_URL)
    .build()

interface AppointmentApiService {
    @GET("appointments")
    suspend fun getAppointmentsByEmployeeId(@Query("employee.id") employeeId : String): List<Appointment>

    @GET("appointments/{id}")
    suspend fun getAppointment(
        @Path("id") id: String
    ): Appointment

    @POST("appointments")
    suspend fun createAppointment(@Body user: Appointment): PostResponse

    @PUT("appointments/{id}")
    suspend fun updateAppointment(@Path("id") id: String, @Body updates: Appointment): PutResponse
}

object AppointmentApi {
    val retrofitService: AppointmentApiService by lazy { retrofit.create(AppointmentApiService::class.java) }
}