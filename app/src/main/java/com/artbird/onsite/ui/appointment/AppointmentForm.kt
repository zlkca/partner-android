package com.artbird.onsite.ui.appointment

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.artbird.onsite.domain.Account
import com.artbird.onsite.domain.Address
import com.artbird.onsite.domain.Client2
import com.artbird.onsite.ui.address.AddressForm
import com.artbird.onsite.ui.components.*
import com.artbird.onsite.ui.theme.SLTheme
import com.artbird.onsite.ui.utils.getCurrentDateString
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun AppointmentForm(
    navController: NavController,
    client: Client2,
//    addressMode: String="new",
    address: Address=Address(),
    title: String,
    notes: String,
    date: String,
    startTime: String,
    endTime: String,
    error: Map<String, String> = mapOf<String, String>(),
    onValChange: (name: String, value: String) -> Unit,
    onSubmit: () -> Unit = {},
//    onUserChange:(name: String) -> Unit = {},
){
    val verticalScrollState = rememberScrollState()
//    val addressOptions = listOf<RadioOption>(
//        RadioOption("r1", "new", "Use a new Address"),
//        RadioOption("r2", "existing", "Use an existing Address")
//    )
//    var addressMode by remember {
//        mutableStateOf("new")
//    }

    fun getTime(h: Int, m: Int): String {
        val dateTime = LocalDateTime.now().withHour(h).withMinute(m)
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        return dateTime.format(formatter)
    }

    Column(modifier = Modifier
        .padding(12.dp)
    ) {

//            Input(
//                readOnly = true,
//                value = if(client.id.isNotEmpty()) client.username else "Select Client",
////                onValueChange = { onUserChange("client", it.username) },
//                label = "Client",
//            )
//            Text(
//                text = if(client.id!!.isNotEmpty()) client.account.username else "Select Client",
//                modifier=Modifier.clickable {
//                    onUserChange("client")
//                }
//            )
        FormActionBar(
            onCancel = {
//                if(appointmentId!= "new"){
//                    navController.navigate("appointments/$appointmentId")
//                }else{
//                    navController.navigate("appointments")
//                }
            },
            onSave = onSubmit
        )

        Column(modifier = Modifier
//            .padding(12.dp)
            .verticalScroll(verticalScrollState))
        {
            OutlinedTextField(
                label = { Text("Client", color = MaterialTheme.colorScheme.onBackground) },
                value = client.account.username,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("search/client/new") // $appointmentId
                    },
                placeholder = {
                    Text(text = "Click to Select a Client",
                        color = MaterialTheme.colorScheme.onBackground)
                },
                enabled = false,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onBackground)
            )

//            Text("") // to separate
//            RadioGroup(
//                addressOptions,
//                onSelect = {it ->
//                    addressMode = it.value
//                }
//            )

            Text("") // to separate
            AddressForm(address, error, onValChange)


            Input(
                value = title,
                onValueChange = { onValChange("title", it) },
                label = "Appointment",
            )

            Input(
                value = notes,
                onValueChange = { onValChange("notes", it) },
                label = "Notes",
            )

            DatePicker(LocalContext.current, "Date", date, { y,m,d ->
                val dateTime =
                    LocalDateTime.now().withYear(y).withMonth(m).withDayOfMonth(d)
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                onValChange("date", dateTime.format(formatter))
            })

            Row(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp, top = 8.dp)
            ) {
                TimePicker(LocalContext.current, "Start Time", startTime,
                    onValueChange = { h, m -> onValChange("startTime", getTime(h, m)) },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )

                TimePicker(LocalContext.current, "End Time", endTime,
                    onValueChange = { h, m -> onValChange("endTime", getTime(h, m))},
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                )
            }
        }
    }
}

@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewAppointmentForm(){
    val client = Client2("1", firstName = "Jet", lastName = "Lee", account = Account("1", "Jet", email="jet@gmail.com", phone="416-123-4567"))
    val address= Address("2", "", "235", "Front St", "Toronto", "ON", "L3R 0C7")

    SLTheme {
        AppointmentForm(
            rememberNavController(),
            client,
            address,
            "",
            "",
            "2022-10-06",
            "10:00",
            "11:45",
            onValChange = {f,v -> }
        )
    }
}