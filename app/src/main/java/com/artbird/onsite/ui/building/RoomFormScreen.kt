package com.artbird.onsite.ui.building

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.artbird.onsite.domain.Building
import com.artbird.onsite.domain.Floor
import com.artbird.onsite.domain.Room
import com.artbird.onsite.ui.components.FormActionBar
import com.artbird.onsite.ui.components.Input

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomFormScreen(
    navController: NavController,
    buildingViewModel: BuildingViewModel,
    buildingId: String?,
    floorId: String,
    roomId: String
){
    val building by buildingViewModel.building.observeAsState()

    var name by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    val verticalScrollState = rememberScrollState()
    var floor by remember { mutableStateOf(Floor("","","", listOf())) }
    var room: Room by remember { mutableStateOf(Room("","","")) }

    LaunchedEffect(key1 = buildingId) {
        if (buildingId != "new" && buildingId != null) {
            buildingViewModel.getBuilding(buildingId!!)
        }
    }

    LaunchedEffect(key1 = building) {
        if (building != null && roomId != "new") {
            floor = building!!.floors.find {it._id == floorId}!!

            if(floor != null && floor!!.rooms.isNotEmpty()) {
                room = floor!!.rooms.find { it._id == roomId }!!
                name = room!!.name
                notes = room!!.notes
            }
        }
    }

    fun handleSubmit(){
        // create new room
        val mFloors = ArrayList<Floor>();
        building!!.floors.forEach { it ->
            if(it._id == floorId){
                val mRooms = ArrayList<Room>();

                if(roomId == "new" || roomId == "") {
                    it!!.rooms.forEach { r ->
                        mRooms.add(r);
                    }
                    mRooms.add(Room("", name, notes))
                    mFloors.add(Floor(it._id, it.name, it.notes, mRooms))
                }else{
                    it!!.rooms.forEach { r ->
                        if(roomId == r._id){
                            mRooms.add(Room(r._id, name, notes))
                        }else{
                            mRooms.add(r);
                        }
                    }
                    mFloors.add(Floor(it._id, it.name, it.notes, mRooms))
                }
            }else{
                mFloors.add(it)
            }
        }

        if (buildingId != null) {
            buildingViewModel.updateBuilding(
                buildingId,
                Building(
                    _id = "",
                    name = building!!.name,
                    notes = building!!.notes,
                    appointment = building!!.appointment,
                    floors = mFloors
                )
            )
        }
//        buildingViewModel.getBuildingsByAppointmentId(building!!.appointment._id!!)
        navController.navigate("buildings/${buildingId}/floors/${floorId}")
    }


    fun getRoomLabel(item: Room, name: String): String {
        return item.name
    }

//    fun handleDelete(){
//    }
    Column(modifier = Modifier
        .padding(12.dp)
        .verticalScroll(verticalScrollState)) {

        FormActionBar(
            onCancel = {
                if(roomId != "new"){
                    navController.navigate("buildings/${buildingId}/floors/${floorId}/rooms/${roomId}")
                }else{
                    navController.navigate("buildings/${buildingId}/floors/${floorId}")
                }
            },
            ::handleSubmit
        )

        Input(
            value = name,
            onValueChange = { name = it },
            label = "Room Name",
        )

        Input(
            value = notes,
            onValueChange = { notes = it },
            label = "Notes",
        )
    }
}