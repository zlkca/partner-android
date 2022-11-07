package com.artbird.onsite.domain

// For the client list
data class Client (
    val id: String = "",
    val username: String = "",
    val email: String = "",
    val phone: String = "",
    val status: String = "",

//    @Json(name="imageurl") val imageUrl: String,
    // client related
    val firstName: String = "",
    val middleName: String = "",
    val lastName: String = "",
    val accountId: String = "",
    val addressId: String = "",
    var recommenderId: String = "",
    val created: String = "",
    val updated: String = "",
)

data class ClientDetails (
    var id: String = "",
    var firstName: String = "",
    var middleName: String = "",
    var lastName: String = "",
    var address: Address = Address(),
    var account: AccountDetails = AccountDetails(),
    var recommender: BaseAccount = BaseAccount(),
    var created: String = "",
    var updated: String = "",
    var stage: Stage = Stage(),
    var records: List<BaseProject> = listOf()
)

data class BaseClient (
    var id: String = "",
    var account: BaseAccount = BaseAccount()
)
