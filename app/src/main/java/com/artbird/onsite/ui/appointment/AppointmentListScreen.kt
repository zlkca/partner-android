package com.artbird.onsite.ui.appointment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.artbird.onsite.domain.Account
//import com.artbird.onsite.ui.appointments.AppointmentList
//import com.artbird.onsite.ui.appointments.AppointmentViewModel
import com.artbird.onsite.ui.components.DropdownMenuItem


@Composable
fun AppointmentListScreen(
    navController: NavController,
    appointmentViewModel: AppointmentViewModel,
    employee: Account,
) {
    val appointments by appointmentViewModel.appointments.observeAsState()
//    val roles: List<Role> by roleViewModel.roles.observeAsState(arrayListOf())

    var selectedIndex by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = employee) {
        if (employee.id.isNotEmpty()) {
            appointmentViewModel.getAppointmentsByEmployeeId(employee.id)
        }
    }

    fun handleSelectAppointment(index: Int) {
        selectedIndex = index
        val appointment = appointments!![index]
        navController.navigate("appointments/${appointment._id}")
    }

    fun handleEdit(index: Int){
        selectedIndex = index
//        val appointments = appointmentss!![index]
//        navController.navigate("appointmentss/${appointments.id}/form")
    }

    val menus: List<DropdownMenuItem> = listOf(
        DropdownMenuItem("edit", "Edit", Icons.Outlined.Edit, "Edit", ::handleEdit),
    )

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {

            AppointmentList(
                navController,
                appointments = appointments!!,
                selectedIndex,
                onSelect = ::handleSelectAppointment,
//                onSelectMenu = { index -> selectedIndex = index },
//                menus = menus
            )
    }
}
