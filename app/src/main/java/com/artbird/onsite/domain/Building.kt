package com.artbird.onsite.domain


data class BaseEntity(
    var _id: String,
    var name: String,
)

data class Room(
    var _id: String,
    var name: String,
    var notes: String,
//    var floor: BaseEntity,
)

//data class RoomListItem(
//    var _id: String,
//    var name: String,
//    var notes: String,
//    val onClick: (it: Int) -> Unit
//)

data class Floor(
    var _id: String,
    var name: String,
    var notes: String,
    var rooms: List<Room>,
//    var building: BaseEntity,
)

data class Building(
    var _id: String = "",
    var name: String = "",
    var notes: String = "",
    var floors: List<Floor> = listOf(),
    var appointment: BaseAppointment
)

// Api
// -- populateDefaultArea
// -- populateDefaultFloor
// -- populateDefaultRoom
// -- removeRoom
// -- removeFloor
// -- removeArea