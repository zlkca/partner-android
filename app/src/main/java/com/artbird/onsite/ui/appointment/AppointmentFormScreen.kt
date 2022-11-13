package com.artbird.onsite.ui.appointment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.artbird.onsite.domain.Appointment
import com.artbird.onsite.domain.BaseAccount
import com.artbird.onsite.domain.BaseClient
import com.artbird.onsite.domain.Client2
import com.artbird.onsite.ui.components.*
import com.artbird.onsite.ui.utils.getDate
import com.artbird.onsite.ui.utils.getTime
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun AppointmentFormScreen(
    navController: NavController,
    appointmentId: String?, // 'new' or appointmentId
    appointmentViewModel: AppointmentViewModel,
    user: BaseAccount, // logged in user
    client: Client2,
){
    val appointment by appointmentViewModel.appointment.observeAsState()
//    var client by remember { mutableStateOf(
//        BaseClient(id= "", account=BaseAccount(id="", username=""))
//    ) }

    var title by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var start by remember { mutableStateOf("") }
    var end by remember { mutableStateOf("") }

    var error by remember { mutableStateOf(mutableMapOf<String, String>())}

    val verticalScrollState = rememberScrollState()

    LaunchedEffect(key1 = appointmentId) {
        if (appointmentId != null && appointmentId != "new") {
            appointmentViewModel.getAppointment(appointmentId)
        }
    }

    LaunchedEffect(key1 = appointment){
        if(appointment != null && appointmentId != "new") {
//            client = appointment?.client!!
            title = appointment?.title!!
            notes = appointment?.notes!!
            date = getDate(appointment?.start!!)
            start = getTime(appointment?.start!!)
            end = getTime(appointment?.end!!)
        }
    }

    fun handleChangeDate(y: Int, m: Int, d: Int) {
        val dateTime =
            LocalDateTime.now().withYear(y).withMonth(m).withDayOfMonth(d)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        date = dateTime.format(formatter)
    }

    fun getTime(h: Int, m: Int): String {
        val dateTime = LocalDateTime.now().withHour(h).withMinute(m)
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        return dateTime.format(formatter)
    }

    fun validate(): Boolean {
        var e = mutableMapOf<String, String>()
        if(client.id == "") {
            e["client"] = "Please select a client"
        }

        if(date == ""){
            e["date"] = "Please select a date"
        }

        if(start == ""){
            e["start"] = "Please select a start time"
        }

        if(end == ""){
            e["end"] = "Please select an end time"
        }

        if(start == ""){
            e["start"] = "Please select a start time"
        }

        if(end == ""){
            e["end"] = "Please select an end time"
        }

        if(start != "" && end != ""){
            val formater = SimpleDateFormat("HH:mm:ss")

            val st = formater.parse(start);
            val et = formater.parse(end);

            if(st.after(et)){
                e["end"] = "End time can not before Start Time"
            }
        }

        error = e

        return e.isEmpty()
    }

    fun handleSubmit(){
        if(validate()) {
            if (appointmentId != null && appointmentId != "new") {
                val data = Appointment(
                    _id = "",
                    title,
                    notes,
                    start = "$date $start",
                    end = "$date $end",
                    type = "sales",
                    client = BaseClient(client.id, BaseAccount(client.account.id, client.account.username)),
                    employee = BaseAccount(appointment?.employee!!.id,
                        appointment?.employee!!.username),
                    createBy = BaseAccount(appointment?.createBy!!.id,
                        appointment?.createBy!!.username),
                )
                appointmentViewModel.updateAppointment(appointment?._id!!, data)
            } else {
                val data = Appointment(
                    _id = "",
                    title,
                    notes,
                    start = "$date $start",
                    end = "$date $end",
                    type = "sales",
                    client = BaseClient(client.id, BaseAccount(client.account.id, client.account.username)),
                    employee = BaseAccount(user.id, user.username),
                    createBy = BaseAccount(user.id, user.username),
                )
                appointmentViewModel.createAppointment(data);
            }

            // refresh list
            appointmentViewModel.getAppointmentsByEmployeeId(user.id!!)
            navController.navigate("appointments")
        }
    }



    Column(modifier = Modifier
        .padding(12.dp)
        .verticalScroll(verticalScrollState)) {

        FormActionBar(
            onCancel = {
                if(appointmentId!= "new"){
                    navController.navigate("appointments/$appointmentId")
                }else{
                    navController.navigate("appointments")
                }
            },
            ::handleSubmit
        )

//        if(appointmentId == "new"){
//            Input(
//                readOnly = true,
//                value = if(client.id.isNotEmpty()) client.account.username else "Select Client",
//                onValueChange = {
//                                navController.navigate("search/client")
//                                },
//
//                label = "Client",
//            )
//        }else{

//        }

//            if(client.id!!.isNotEmpty()) {
                Text(
                    text = if(client.id!!.isNotEmpty()) client.account.username else "Select Client",
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            navController.navigate("search/client/$appointmentId")
                        }
                )
//            }else{
//                Row(
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    ActionChip("Select Client", onClick = {
//                        navController.navigate("search/client/$appointmentId")
//                    })
//                }
//            }

            when {
                error.isNotEmpty() && error.containsKey("client") -> {
                        Text(
                            text = error["client"]!!,
                            color = Color.Red,
                            modifier = Modifier
                                .padding(8.dp)
                        )
                }
            }

            Input(
                value = title,
                onValueChange = { title = it },
                label = "Title",
            )

            Input(
                value = notes,
                onValueChange = { notes = it },
                label = "Notes",
            )

            DatePicker(LocalContext.current, "Date", date, ::handleChangeDate)
            when {
                error.isNotEmpty() && error.containsKey("date") -> {
                    Text(
                        text = error["date"]!!,
                        color = Color.Red,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }
            }


            Row(
                modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)
            ) {
                Column(modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
                ) {
                    TimePicker(
                        LocalContext.current,
                        "Start Time",
                        time = start,
                        onValueChange = { h, m -> start = getTime(h, m) },

                    )
                    when {
                        error.isNotEmpty() && error.containsKey("start") -> {
                            Text(
                                text = error["start"]!!,
                                color = Color.Red,
                                modifier = Modifier
                                    .padding(8.dp)
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    TimePicker(
                        LocalContext.current,
                        "End Time",
                        time = end,
                        onValueChange = { h, m -> end = getTime(h, m) },
                    )

                    when {
                        error.isNotEmpty() && error.containsKey("end") -> {
                            Text(
                                text = error["end"]!!,
                                color = Color.Red,
                                modifier = Modifier
                                    .padding(8.dp)
                            )
                        }
                    }
                }
            }

    }
}