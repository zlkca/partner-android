package com.artbird.onsite.domain

data class FormError(
    val code: Int = 200,
    val field:String = "",
    val message:String = "No Error",
)