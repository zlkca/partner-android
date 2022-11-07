package com.artbird.onsite.ui.building

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.appointment.AppointmentViewModel
import com.artbird.onsite.ui.components.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuildingFormScreen(
    navController: NavController,
    appointmentViewModel: AppointmentViewModel,
    buildingViewModel: BuildingViewModel,
    appointmentId: String?,
    buildingId: String?,
) {
    val appointment by appointmentViewModel.appointment.observeAsState()
    val building by buildingViewModel.building.observeAsState()

    var name by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var floors: List<Floor> by remember { mutableStateOf(arrayListOf()) }

    val verticalScrollState = rememberScrollState()

    LaunchedEffect(key1 = appointmentId) {
        if (appointmentId != null && appointmentId != "new") {
            appointmentViewModel.getAppointment(appointmentId)
        }
    }

    LaunchedEffect(key1 = buildingId) {
        if (buildingId != null && buildingId != "new") {
            buildingViewModel.getBuilding(buildingId)
        }
    }

    LaunchedEffect(key1 = building) {
        if (building != null && buildingId != "new") {
            name = building!!.name
            notes = building!!.notes
            floors = building!!.floors
        }
    }

    fun handleSubmit() {
        if(buildingId == "new") {
            buildingViewModel.createBuilding(
                Building(
                    "",
                    name,
                    notes,
                    appointment = BaseAppointment(appointmentId, appointment?.title!!),
                    floors = listOf(),
                )
            )
            navController.navigate("buildings")
        }else{
            buildingViewModel.updateBuilding(
                buildingId!!,
                Building(
                    "",
                    name,
                    notes,
                    appointment = BaseAppointment(appointmentId, appointment?.title!!),
                    floors = floors,
                )
            )
            navController.navigate("buildings/${buildingId}")
        }
    }

    Column(modifier = Modifier
        .padding(8.dp)
        .verticalScroll(verticalScrollState)
    ) {

        FormActionBar(
            onCancel = {
                navController.navigate("buildings")
            },
            ::handleSubmit
        )

        Input(
            value = name,
            onValueChange = { name = it },
            label = "Building Name",
        )

        Input(
            value = notes,
            onValueChange = { notes = it },
            label = "Notes",
        )
    }
}