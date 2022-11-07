package com.artbird.onsite.domain

data class Address (
    var id: String = "", // account id
    var unitNumber: String = "",
    var streetNumber: String = "",
    var streetName: String = "",
    var city: String = "",
    var province: String = "",
    var postcode: String = "",
    var lat: Double = 0.0,
    var lng: Double = 0.0,
)