@file:OptIn(ExperimentalMaterial3Api::class)

package com.artbird.onsite.ui.appointment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.artbird.onsite.domain.Appointment
import com.artbird.onsite.domain.Account
import com.artbird.onsite.ui.components.*
import com.artbird.onsite.ui.utils.toLocalDateTime
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun AppointmentSchedulerScreen(
    user: Account,
    navController: NavController,
    viewModel: AppointmentViewModel,
    onMeasure: (appointmentId: String) -> Unit,
    onAdd: () -> Unit,
){

    val appointments: List<Appointment> by viewModel.appointments.observeAsState(arrayListOf())
    var events: List<Event> by remember { mutableStateOf(arrayListOf()) }
    var selectedDate by remember {
        mutableStateOf(LocalDate.now())
    }

    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    LaunchedEffect(key1 = appointments){
        if(appointments != null) {
            events = appointments.map { Event(
                id=it._id,
                name=it.title,
                color=Color(0xFFAFBBF2),
                start= toLocalDateTime(it.start), // LocalDateTime Object
                end=toLocalDateTime(it.end),
                description = it.client.username
            ) }
        }
    }

    LaunchedEffect(key1 = user.id){
        if(user.id!!.isNotEmpty()) {
            viewModel.getAppointmentsByEmployeeId(user.id)
        }
    }

    fun handleViewEvent(e: Event){
        onMeasure(e?.id!!)
        navController.navigate("appointments/${e.id}")
    }

    fun handleMeasure(e: Event){
        onMeasure(e?.id!!)
        navController.navigate("buildings/${e.id}")
    }


  Column(modifier = Modifier.padding(16.dp)) {
      ListActionBar(items = listOf(
          ActionChip("Appointment", onClick = {
              onAdd()
              navController.navigate("appointments/new/form")
          }),
      ))

      Scheduler(
          events=events,
          minDate= selectedDate,
          onClickEvent = ::handleViewEvent,
          onSelect = ::handleMeasure,
          onNextDay = {selectedDate = selectedDate.plusDays(1)},
          onPrevDay = {selectedDate = selectedDate.plusDays(-1)}
      )
  }
}