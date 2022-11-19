package com.artbird.onsite.ui.appointment

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.components.ActionChip
import com.artbird.onsite.ui.components.Body3
import com.artbird.onsite.ui.components.ListActionBar
import com.artbird.onsite.ui.components.Title2
import com.artbird.onsite.ui.theme.SLTheme
import com.artbird.onsite.ui.utils.getAddressString


@Composable
fun AppointmentListItem(item: Appointment2, selected: Boolean, index:Int){
    val colorScheme = MaterialTheme.colorScheme
    val client = item.client

    Column(){
        if(item.address.streetName.isNotEmpty()){
            Title2(
                text = getAddressString(item.address),
                color = if (selected) colorScheme.onPrimary else colorScheme.onBackground,
            )
            Body3(
                text = if (client.firstName.isNotEmpty()) "${client.firstName} ${client.lastName}" else client.account.username,
                color = if (selected) colorScheme.onPrimary else colorScheme.onBackground
            )
        }else {
            Title2(
                text = if (client.firstName.isNotEmpty()) "${client.firstName} ${client.lastName}" else client.account.username,
                color = if (selected) colorScheme.onPrimary else colorScheme.onBackground
            )
        }

        Body3(
            text = "From ${item.start} to ${item.end}",
            color = if (selected) colorScheme.onPrimary else colorScheme.onBackground,
        )
    }
}

@Composable
fun AppointmentList(
    navController: NavController,
    appointments: List<Appointment2>,
    selectedIndex: Int,
    onSelect: (index: Int) -> Unit = { i: Int -> },
) {
    Column() {
        ListActionBar(items = listOf(
            ActionChip("Appointment", onClick = {navController.navigate("appointments/new/form")}),
        ))
        com.artbird.onsite.ui.components.List<Appointment2>(
            appointments,
            selectedIndex,
            onSelect = onSelect,
            itemContent = { it, selected, index ->
                AppointmentListItem(item=it, selected=selected, index =index)
            }
        )
    }

}

@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewAppointmentList(){
    val appointments = listOf<Appointment2>(
        Appointment2(
            "1",
            title="My first appointment",
            client = Client2(
                firstName = "Rick",
                lastName="Grimes",
                account = Account(username="rick", email="rick@shutter.ca", phone="123-456-7890"),
                created="2022-11-07"
            ),
            employee = Account(username="sales", email="sales@shutterlux.ca", phone="123-456-7890"),
            start = "2022-11-16 16:00:00",
            end = "2022-11-16 16:30:00",
            type = "sales",
            notes = "",
            createBy = Account(username="sales", email="sales@shutterlux.ca", phone="123-456-7890"),
        ),
        Appointment2(
            "2",
            title="My second appointment",
            client = Client2(
                firstName = "Lori",
                lastName="Grims",
                account = Account(username="lori", email="lori@shutter.ca", phone="123-456-7890"),
                created="2022-11-07"
            ),
            employee = Account(username="sales", email="sales@shutterlux.ca", phone="123-456-7890"),
            start = "2022-11-16 16:00:00",
            end = "2022-11-16 16:30:00",
            type = "sales",
            notes = "",
            createBy = Account(username="sales", email="sales@shutterlux.ca", phone="123-456-7890"),
        ),
        Appointment2(
            "3",
            title="My 3rd appointment",
            client = Client2(
                firstName = "Dale",
                lastName="Horvath",
                account = Account(username="dale", email="dale@shutter.ca", phone="123-456-7890"),
                created="2022-11-07"
            ),
            employee = Account(username="sales", email="sales@shutterlux.ca", phone="123-456-7890"),
            start = "2022-11-16 16:00:00",
            end = "2022-11-16 16:30:00",
            type = "sales",
            notes = "",
            createBy = Account(username="sales", email="sales@shutterlux.ca", phone="123-456-7890"),
        ),
    )
    SLTheme {
        AppointmentList(
            rememberNavController(),
            appointments,
            0
        )
    }
}