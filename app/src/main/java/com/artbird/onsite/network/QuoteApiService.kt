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

interface QuoteApiService {
    @GET("quotes")
    suspend fun getQuotes(): List<Quote>

    @POST("quotes")
    suspend fun createQuote(@Body quoteReq: QuoteRequest): List<Quote>

    @GET("quotes/appointments/{appointmentId}")
    suspend fun getQuotesByAppointmentId(@Path("appointmentId") appointmentId: String): List<Quote>?

    @GET("quotes/{id}")
    suspend fun getQuote(@Path("id") id: String): Quote?

    @DELETE("quotes/{id}")
    suspend fun deleteQuote(@Path("id") id: String): DeleteResponse?
}

object QuoteApi {
    val retrofitService: QuoteApiService by lazy { retrofit.create(QuoteApiService::class.java) }
}