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
import com.artbird.onsite.ui.appointment.AppointmentViewModel
import com.artbird.onsite.ui.components.FormActionBar
import com.artbird.onsite.ui.components.Input

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloorFormScreen(
    navController: NavController,
    appointmentViewModel: AppointmentViewModel,
    buildingViewModel: BuildingViewModel,
    buildingId: String?,
    floorId: String,
){
    val appointment by appointmentViewModel.appointment.observeAsState()

    var name by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    val verticalScrollState = rememberScrollState()

    val building by buildingViewModel.building.observeAsState()
    var floors: List<Floor> by remember { mutableStateOf(arrayListOf()) }

    LaunchedEffect(key1 = buildingId) {
        if (buildingId != "new" && buildingId != null) {
            buildingViewModel.getBuilding(buildingId)
        }
    }

    LaunchedEffect(key1 = building) {
        if (building != null) {
            floors = building!!.floors

            if(floorId != "new"){
                val floor = floors.find { it._id == floorId}
                name = floor!!.name
                notes = floor!!.notes
            }
        }
    }

    fun handleSubmit(){
        val mFloors = ArrayList<Floor>();
        if(floorId == "new") { // create new floor
            floors.forEach {
                mFloors.add(it);
            }
            mFloors.add(Floor("", name, notes, listOf()))
        }else{
            floors.forEach {
                if(it._id == floorId){
                    mFloors.add(Floor(it._id, name, notes, it.rooms));
                }else{
                    mFloors.add(it);
                }
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
            // refresh list
            buildingViewModel.getBuilding(buildingId)
        }

        navController.navigate("buildings/${buildingId}")
    }

    fun handleCancel(){
        navController.navigate("buildings/${buildingId}")
    }


    fun getRoomLabel(item: Room, name: String): String {
        return item.name
    }

//    fun handleDelete(){
//    }
    Column(modifier = Modifier
        .padding(12.dp)
        .verticalScroll(verticalScrollState)) {
//        if(floor !== null) {

            FormActionBar(::handleCancel, ::handleSubmit)

            Input(
                value = name,
                onValueChange = { name = it },
                label = "Floor Name",
            )

            Input(
                value = notes,
                onValueChange = { notes = it },
                label = "Notes",
            )

//        }
    }
}