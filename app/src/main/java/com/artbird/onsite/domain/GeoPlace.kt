package com.artbird.onsite.domain

import android.provider.Contacts
import com.squareup.moshi.Json

// For locationIQ api
data class PredictPlaces(
    @field:Json(name = "photo") val places: List<GeoPlace>
)

data class LocationIQAddress(
    @field:Json(name = "house_number") val streetNumber: String="",
    @field:Json(name = "road") val streetName: String="",
    val suburb: String="",
    val city: String="",
    val state: String="",
    val postcode: String="",
    val country: String="",
)

data class GeoPlace(
    @field:Json(name = "place_id") val id: String="",
    @field:Json(name = "lat") val lat: Double=0.0,
    @field:Json(name = "lon") val lng: Double=0.0,
    @field:Json(name = "display_address") val displayAddress: String="",
    @field:Json(name = "address") val address: LocationIQAddress=LocationIQAddress()
)

// For google place api
data class GooglePredictionTerm(
    val offset: Int,
    val value: String
)

data class GooglePrediction(
    val description: String,
    val terms: List<GooglePredictionTerm>
)

data class GooglePredictionsResponse(
    val predictions: List<GooglePrediction> = listOf()
)