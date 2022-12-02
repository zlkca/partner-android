package com.artbird.onsite.ui.building

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.appointment.AppointmentViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuildingFormScreen(
    navController: NavController,
//    appointmentViewModel: AppointmentViewModel,
    buildingViewModel: BuildingViewModel,
    appointmentId: String,
    buildingId: String,
) {
    val buildingData by buildingViewModel.building.observeAsState()
    var building: Building by remember{ mutableStateOf(Building())}

//    LaunchedEffect(key1 = appointmentId) {
//        if (appointmentId != null && appointmentId != "new") {
//            appointmentViewModel.getAppointment(appointmentId)
//            building.copy(appointmentId = appointmentId)
//        }
//    }

    LaunchedEffect(key1 = buildingId) {
        if (buildingId != null && buildingId != "new") {
            buildingViewModel.getBuilding(buildingId)
        }
    }

    LaunchedEffect(key1 = buildingData) {
        if (buildingData != null && buildingId != "new") {
            building = buildingData!!
        }
    }

    fun handleSubmit() {
        if(buildingId == "new") {
            buildingViewModel.createBuilding(
                building.copy(
                    floors = listOf(),
                )
            )
            navController.navigate("buildings")
        }else{
            buildingViewModel.updateBuilding(
                buildingId!!,
                building.copy(
                    floors = buildingData!!.floors,
                )
            )
            navController.navigate("buildings/${buildingId}")
        }
    }

    BuildingForm(
        navController,
        building!!,
        onChange = {f, value ->
            when(f){
                "name" -> building = building.copy(name = value)
                "notes" -> building = building.copy(notes = value)
            }
        },
        onSave = ::handleSubmit
    )
}