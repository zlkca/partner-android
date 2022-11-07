package com.artbird.onsite.ui.appointment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.BaseAccount
import com.artbird.onsite.ui.components.DatePicker
import com.artbird.onsite.ui.components.Input
import com.artbird.onsite.ui.components.TimePicker

@Composable
fun AppointmentForm(
    client: BaseAccount,
    title: String,
    notes: String,
    date: String,
    startTime: String,
    endTime: String,
    onValChange: (name: String, value: String) -> Unit,
    onDateChange: (y: Int, m: Int, d: Int) -> Unit,
    onTimeChange:(name: String, h: Int, m: Int) -> Unit,
    onUserChange:(name: String) -> Unit = {},
){
    val verticalScrollState = rememberScrollState()

    Column(modifier = Modifier
        .padding(12.dp)
        .verticalScroll(verticalScrollState)) {

//            Input(
//                readOnly = true,
//                value = if(client.id.isNotEmpty()) client.username else "Select Client",
////                onValueChange = { onUserChange("client", it.username) },
//                label = "Client",
//            )
            Text(
                text = if(client.id!!.isNotEmpty()) client.username else "Select Client",
                modifier=Modifier.clickable {
                    onUserChange("client")
                }
            )


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


            DatePicker(LocalContext.current, "Date", date, onDateChange)

            Row(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp, top = 8.dp)
            ) {
                TimePicker(LocalContext.current, "Start Time", startTime,
                    onValueChange = { h, m -> onTimeChange("startTime", h, m)},
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )

                TimePicker(LocalContext.current, "End Time", endTime,
                    onValueChange = { h, m -> onTimeChange("endTime", h, m)},
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                )
            }

    }
}