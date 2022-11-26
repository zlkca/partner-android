package com.artbird.onsite.domain


data class Appointment (
    val _id: String,
    val title: String, // has to be title due to Web Calendar UI component
    val notes: String,
    val type: String,
    val start: String,
    val end: String,
    val client: Account,
    val address: Address,
    val employee: Account, // without role
    val createBy: Account,
)

data class Appointment2 (
    val _id: String = "",
    val title: String = "", // has to be title due to Web Calendar UI component
    val notes: String = "",
    val type: String = "",
    val start: String = "",
    val end: String = "",
//    val projectId: String = "",
    val client: Account = Account(),
    val address: Address = Address(),
    val employee: Account = Account(), // without role
    val createBy: Account = Account(),
)

data class BaseAppointment(
    val _id: String = "",
    val title: String = "", // has to be title due to Web Calendar UI component
)
