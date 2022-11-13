package com.artbird.onsite.repository

import com.artbird.onsite.domain.GeoPlace

import com.artbird.onsite.network.GeoPlaceApi
//import com.artbird.onsite.network.GooglePlaceApi
//import com.artbird.onsite.domain.GooglePredictionsResponse

import retrofit2.Response

class AddressRepository(){

    // for locationIQ
    suspend fun getAddressPredictions(input: String): Response<List<GeoPlace>> {
        return GeoPlaceApi.retrofitService.getAddressPredictions(
            countryCodes = "ca",
            key="pk.a7e295eab209d1ed105df718c9a6b192",
            input
        )
    }

    // for google
//    suspend fun getAddressPredictions(input: String): Response<GooglePredictionsResponse> {
//        return GooglePlaceApi.retrofitService.getAddressPredictions(
//            components="country:CA",
//            key="AIzaSyAewkzqiljzVnh8vEcPKovePz3lCY2YCD8",
//            types="address",
//            input
//        )
//    }

}