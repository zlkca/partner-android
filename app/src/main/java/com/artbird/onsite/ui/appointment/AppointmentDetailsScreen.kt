package com.artbird.onsite.ui.appointment

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController

import com.artbird.onsite.domain.*


@Composable
fun AppointmentDetailsScreen(
    navController: NavController,
    appointmentId: String, // 'new' or appointmentId
    appointmentViewModel: AppointmentViewModel,
    onSelectAppointment: (a: Appointment) -> Unit = {},
) {
    val appointment by appointmentViewModel.appointment.observeAsState(Appointment())

    LaunchedEffect(key1 = appointmentId) {
        if (appointmentId != null && appointmentId != "new") {
            appointmentViewModel.getAppointment(appointmentId)
        }
    }

    LaunchedEffect(key1 = appointment){
        if(appointment != null) {
             onSelectAppointment(appointment!!)
        }
    }

    AppointmentDetails(
        navController,
        appointment!!
    )
}
