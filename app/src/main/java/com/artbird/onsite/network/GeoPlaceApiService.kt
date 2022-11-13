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
//    .baseUrl(APIConstant.GOOGLE_MAP_API_URL)
    .baseUrl(APIConstant.LOCATIONIQ_API_URL)
    .build()

interface GeoPlaceApiService {
    @GET("autocomplete")
    suspend fun getAddressPredictions(
        @Query("countrycodes") countryCodes: String = "ca",
        @Query("key") key: String,
        @Query("q") input: String
    ): Response<List<GeoPlace>>
}

object GeoPlaceApi {
    val retrofitService: GeoPlaceApiService by lazy { retrofit.create(GeoPlaceApiService::class.java) }
}

//interface GooglePlaceApiService {
//    @GET("maps/api/place/autocomplete/json")
//    suspend fun getAddressPredictions(
//        @Query("components") components: String, // ?components=country:ca
//        @Query("key") key: String,
//        @Query("types") types: String = "address",
//        @Query("input") input: String
//    ): Response<GooglePredictionsResponse>
//
//    companion object{
//        const val BASE_URL = "https://maps.googleapis.com/"
//    }
//}
//
//object GooglePlaceApi {
//    val retrofitService: GooglePlaceApiService by lazy { retrofit.create(GooglePlaceApiService::class.java) }
//}