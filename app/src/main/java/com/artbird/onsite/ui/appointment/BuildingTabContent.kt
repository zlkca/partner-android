package com.artbird.onsite.ui.appointment

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.artbird.onsite.domain.Building
import com.artbird.onsite.domain.Floor
import com.artbird.onsite.domain.Room
import com.artbird.onsite.ui.building.BuildingViewModel
//import com.shutterlux.onsite.ui.building.FloorListView
//import com.shutterlux.onsite.ui.measure.BuildingListView
import com.artbird.onsite.ui.window.WindowViewModel

@Composable
fun BuildingTabContent(
    appointmentId: String?,
    navController: NavController,
    buildingViewModel: BuildingViewModel,
//    quoteViewModel: QuoteViewModel,
    windowViewModel: WindowViewModel,
) {
    var step by remember { mutableStateOf("buildings") } // buildings -> floors -> rooms
    var buildingIndex by remember { mutableStateOf(0) }
    var floorIndex by remember { mutableStateOf(0) }
    var roomIndex by remember { mutableStateOf(0) }

    var building: Building
    var floor: Floor
    var room: Room

    val buildings by buildingViewModel.buildings.observeAsState()
//    val quotes by quoteViewModel.quotes.observeAsState()

    LaunchedEffect(key1 = appointmentId) {
        if (appointmentId != null) {
            buildingViewModel.getBuildingsByAppointmentId(appointmentId)
//            quoteViewModel.getQuotesByAppointmentId(appointmentId)
        }
    }

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
    }

//    if(step == "buildings"){
//        BuildingListView(buildings, ::handleNext)
//    }else if(step == "floors"){
//        building = buildings?.get(buildingIndex)!!
//        FloorListView(buildingIndex, building, ::handleNext, ::toPreviousView  )
//    }else if(step == "rooms"){
//        floor = buildings?.get(buildingIndex)!!.floors?.get(floorIndex)!!
//        RoomListView(floorIndex, floor.rooms, ::handleNext, ::toPreviousView )
//    }else{
//        room = buildings?.get(buildingIndex)!!.floors?.get(floorIndex)!!.rooms?.get(roomIndex)!!
//        WindowListView(roomIndex, roomId = room._id, windowViewModel = windowViewModel, ::toPreviousView )
//    }
}