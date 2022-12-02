package com.artbird.onsite.ui.measure

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.appointment.AppointmentViewModel
import com.artbird.onsite.ui.building.BuildingViewModel
import com.artbird.onsite.ui.window.WindowViewModel

@Composable
fun MeasureScreen(
    navController: NavController,
    appointmentId: String?,
    appointmentViewModel: AppointmentViewModel,
    buildingViewModel: BuildingViewModel,
    windowViewModel: WindowViewModel,
) {
    val buildings by buildingViewModel.buildings.observeAsState()
    val appointment by appointmentViewModel.appointment.observeAsState()


    LaunchedEffect(key1 = appointmentId) {
        if (appointmentId != null) {
            buildingViewModel.getBuildingsByAppointmentId(appointmentId)
            appointmentViewModel.getAppointment(appointmentId)
        }
    }

    var step by remember { mutableStateOf("buildings") } // buildings -> floors -> rooms
    var buildingIndex by remember { mutableStateOf(0) }
    var floorIndex by remember { mutableStateOf(0) }
    var roomIndex by remember { mutableStateOf(0) }
    var windowIndex by remember { mutableStateOf(0) }

    var building: Building
    var floor: Floor
    var room: Room
    var window: Window

    val windows by windowViewModel.windows.observeAsState(arrayListOf())


    fun handleNext(s: String, index: Int){
        step = s
        if(s == "floors") {
            buildingIndex = index
        }
        if(s == "rooms") {
            floorIndex = index
        }
        if(s == "windows") {
            roomIndex = index
        }
        if(s == "window") {
            windowIndex = index
        }
    }

    fun toPreviousView(s: String, index: Int){
        step = s
        if(s == "buildings") {
            buildingIndex = index
        }
        if(s == "floors") {
            floorIndex = index
        }
        if(s == "rooms") {
            roomIndex = index
        }
        if(s == "windows") {
            windowIndex = index
        }
    }

    fun handleAdd(type: String) {
        if(appointmentId!=null) {

            if(type == "sample") {
                // create default building
                buildingViewModel.createBuildingSample(
                    Building("",
                        "New Address",
                        "New building",
                        appointmentId = appointmentId,
                        floors = listOf(
                            Floor("", "First Floor", "", rooms = listOf(
                                Room("", "Living Room", ""),
                                Room("", "Family Room", ""),
                                Room("", "Dinning Room", ""),
                                Room("", "Kitchen", ""),
                            )),
                            Floor("", "Second Floor", "", rooms = listOf(
                                Room("", "Master Bedroom", ""),
                                Room("", "Room 1", ""),
                                Room("", "Room 2", ""),
                                Room("", "Room 3", ""),
                                Room("", "Washroom1", ""),
                                Room("", "Washroom2", ""),
                            )),
                        )
                    ),
                )
            }else{
                navController.navigate("appointments/new")
            }
            buildingViewModel.getBuildingsByAppointmentId(appointmentId)
        }

    }

//    if(step == "buildings"){
//        BuildingListView(buildings, ::handleNext, onDelete = ::deleteBuilding, onAdd=::handleAdd)
//    }else if(step == "building-form") {
////        BuildingForm(buildings!![buildingIndex])
//    }else if(step == "floors"){
//        building = buildings?.get(buildingIndex)!!
//        BuildingDetailsView(buildingIndex, building, ::handleNext, ::toPreviousView )
//    }else if(step == "rooms"){
//        building = buildings?.get(buildingIndex)!!
//        floor = building.floors?.get(floorIndex)!!
//        FloorDetailsView(building, floorIndex, floor, ::handleNext, ::toPreviousView )
//    }else if(step == "windows"){
//        building = buildings?.get(buildingIndex)!!
//        floor = building.floors?.get(floorIndex)!!
//        room = floor.rooms?.get(roomIndex)!!
//
//        // load windows
//        LaunchedEffect(key1 = room?._id) {
//            if (room._id != null) {
//                windowViewModel.getWindowsByRoomId(room._id)
//            }
//        }
//        RoomDetailsView(building, floor, roomIndex, room, windowViewModel = windowViewModel, ::handleNext, ::toPreviousView )
//    }else{
//        room = buildings?.get(buildingIndex)!!.floors?.get(floorIndex)!!.rooms?.get(roomIndex)!!
//
//        WindowScreen(
////            navController,
////            windowId: String?,
//            windowIndex,
//            windows,
//            windowViewModel,
//            ::toPreviousView
//        )
//    }

}