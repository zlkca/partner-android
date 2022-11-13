package com.artbird.onsite.domain

//// For the client list
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
//
//data class Client2 (
//    var id: String = "",
//    var firstName: String = "",
//    var middleName: String = "",
//    var lastName: String = "",
//    var address: Address = Address(),
//    var account: Account2 = Account2(),
//    var recommender: BaseAccount = BaseAccount(),
//    var created: String = "",
//    var updated: String = "",
//)

data class Client2 (
    val id: String = "",
    var account: Account = Account(),
//    val address: Address = Address(),
    var recommender: Account = Account(),
    // @Json(name="imageurl") val imageUrl: String,
    // client related
    var firstName: String = "",
    var middleName: String = "",
    var lastName: String = "",
    val created: String = "",
    val updated: String = "",
)

data class BaseClient (
    var id: String = "",
    var account: BaseAccount = BaseAccount()
)
