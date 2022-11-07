package com.artbird.onsite.domain


data class Appointment (
    val _id: String,
    val title: String, // has to be title due to Web Calendar UI component
    val notes: String,
    val type: String,
    val start: String,
    val end: String,
    val client: BaseClient,
    val employee: BaseAccount, // without role
    val createBy: BaseAccount,
)

data class BaseAppointment(
    val _id: String? = null,
    val title: String, // has to be title due to Web Calendar UI component
)
