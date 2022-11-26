package com.artbird.onsite.ui.appointment

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.artbird.onsite.domain.Account
import com.artbird.onsite.domain.Address
import com.artbird.onsite.domain.Appointment2
import com.artbird.onsite.ui.components.*
import com.artbird.onsite.ui.theme.SLTheme
import com.artbird.onsite.ui.utils.getDate
import com.artbird.onsite.ui.utils.getTime

@Composable
fun AppointmentDetails(
    navController: NavController,
    appointment: Appointment2,
){
    val client = appointment.client
    val date = if(appointment?.start!! != null) getDate(appointment?.start!!) else ""
    val start = if(appointment?.start!! != null) getTime(appointment?.start!!) else ""
    val end = if(appointment?.end!! != null) getTime(appointment?.end!!) else ""

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {

        if(appointment != null) {
            DetailsViewActionBar(
                onBack = { navController.navigate("appointments")},
                onEdit = { navController.navigate("appointments/$appointment._id/form") }
            )

            Column(
                modifier = Modifier.padding(8.dp)
            ){
                Title2(text = appointment!!.title)
                Body1(text = appointment!!.address.displayAddress)
                Body1(text = "$date  $start - $end")
                Body1(text = appointment!!.notes)
            }

            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Title2(text = client.username)
                Body1(text = client.email)
                Body1(text = client.phone)
            }

            ListActionBar(items = listOf(
                ActionChip(
                    "Measure",
                    Icons.Filled.Straighten,
                    onClick = {
                        navController.navigate("buildings")
                    }
                ),
                ActionChip(
                    "View Quote",
                    icon = Icons.Filled.ReceiptLong,
                    onClick = {
                        navController.navigate("quotes")
                    }
                ),
            ))
        }
    }
}

@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewAppointmentDetails(){
    val appointment = Appointment2(
        "1",
        title="My first appointment",
        address= Address("2", "", "235", "Front St", "Toronto", "ON", "L3R 0C7",
            displayAddress="235 Front St, Toronto, ON"
        ),
        client = Account(username="rick", email="rick@shutter.ca", phone="123-456-7890"),
        employee = Account(username="sales", email="sales@shutterlux.ca", phone="123-456-7890"),
        start = "2022-11-16 16:00:00",
        end = "2022-11-16 16:30:00",
        type = "sales",
        notes = "This is a test notes",
        createBy = Account(username="sales", email="sales@shutterlux.ca", phone="123-456-7890"),
    );

    SLTheme {
        AppointmentDetails(
            rememberNavController(),
            appointment
        )
    }
}
