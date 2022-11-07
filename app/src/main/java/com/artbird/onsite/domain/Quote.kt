package com.artbird.onsite.domain


data class QuoteWindow(
    var window: BaseEntity,
    var building: BaseEntity,
    var floor: BaseEntity,
    var room: BaseEntity,
    var height: Int,
    var width: Int,
    var area: Float,
    var price: Float,
    var total: Float,
)

data class QuoteRequest(
    var appointment: BaseAppointment,
    var address: String,
    var client: BaseAccount,
    var employee: BaseAccount,
)

data class Quote(
    var _id: String?=null,
    var address: String,
    var appointment: BaseAppointment,
    var client: BaseAccount,
    var employee: BaseAccount,
    var windows: List<QuoteWindow>,
    var subtotal: Float,
    var total: Float,
    var created: String,
    )