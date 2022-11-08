package com.artbird.onsite.domain

// use for the list item
data class Account(
    var id: String = "",
    var username: String = "",
    var password: String = "",
    var email: String = "",
    var phone: String = "",
    var status: String = "",
    var roleId: String = "",
)

data class AccountDetails(
    var id: String = "",
    var username: String = "",
    var email: String = "",
    var phone: String = "",
    var status: String = "",
    var role: Role = Role(),
)

data class BaseAccount(
    var id: String = "",
    var username: String = "",
//    var email: String = "",
)