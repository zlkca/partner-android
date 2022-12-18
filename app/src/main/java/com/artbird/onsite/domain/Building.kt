package com.artbird.onsite.domain


data class BaseEntity(
    var _id: String,
    var name: String,
)

data class Room(
    var _id: String="",
    var name: String="",
    var notes: String="",
)

//data class RoomListItem(
//    var _id: String,
//    var name: String,
//    var notes: String,
//    val onClick: (it: Int) -> Unit
//)

data class Floor(
    var _id: String="",
    var name: String="",
    var notes: String="",
    var rooms: List<Room> = listOf(),
)

data class Building(
    var _id: String = "",
    var name: String = "",
    var notes: String = "",
    var floors: List<Floor> = listOf(),
    var appointmentId: String = ""
)

data class SampleBuildingReqBody(
//    var name: String = "",
//    var notes: String = "",
    var appointmentId: String = "",
)

// Api
// -- populateDefaultArea
// -- populateDefaultFloor
// -- populateDefaultRoom
// -- removeRoom
// -- removeFloor
// -- removeArea