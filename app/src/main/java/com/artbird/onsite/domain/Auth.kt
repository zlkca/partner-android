package com.artbird.onsite.domain

data class Credential(
    val username:String = "",
    val email:String,
    val password:String,
    val oldPassword: String = "",
)

data class Auth(
    val status:String,
    val jwt:String,
    val error: String,
    val account: BaseAccount?=null,
    val role: Role,
)


