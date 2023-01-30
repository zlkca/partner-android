package com.artbird.onsite.domain

data class Credential(
    val username:String = "",
    val email:String = "",
    val password:String = "",
    val oldPassword: String = "",
)

data class Auth(
    val status:String = "",
    val token:String = "",
    val message: String = "",
    val account: Account = Account(),
)

