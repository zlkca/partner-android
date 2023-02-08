package com.artbird.onsite.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit

object APIConstant {
//    const val NONSQL_API_URL = "http://192.168.0.13:5001/"
//    const val RDB_API_URL = "http://192.168.0.13:5000/"

    const val GOOGLE_MAP_API_URL = "https://maps.googleapis.com/"
    const val LOCATIONIQ_API_URL = "https://api.locationiq.com/v1/"
// "https://g5kncyxqmb.execute-api.us-east-1.amazonaws.com/dev/"

    const val NONSQL_API_URL = "https://csowmplge0.execute-api.us-east-1.amazonaws.com/dev/"
    const val RDB_API_URL = "https://osnyayier1.execute-api.us-east-1.amazonaws.com/dev/"
}

//val authInterceptor = Interceptor { chain ->
//    val newRequest = chain.request().newBuilder()
//        .addHeader("Authorization", "Bearer $accessToken")
//        .build()
//    chain.proceed(newRequest)
//}
//
//val httpClient = OkHttpClient.Builder()
//    .addInterceptor(authInterceptor)
//    .build()
//
//val retrofitClient = Retrofit.Builder()
//    .baseUrl("https://api.example.com")
//    .client(httpClient)
//    .build()
