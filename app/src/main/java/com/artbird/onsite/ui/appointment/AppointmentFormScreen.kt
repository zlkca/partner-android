package com.artbird.onsite.ui.appointment

import android.util.Log
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.profile.ProfileViewModel
import com.artbird.onsite.ui.utils.getAddressString
import com.artbird.onsite.ui.utils.getDate
import com.artbird.onsite.ui.utils.getTime
import java.text.SimpleDateFormat

@Composable
fun AppointmentFormScreen(
    navController: NavController,
    appointmentId: String?, // 'new' or appointmentId
    appointmentViewModel: AppointmentViewModel,
    profileViewModel: ProfileViewModel,
    user: Account, // logged in user
){
    val clientProfile by profileViewModel.profile.observeAsState(Profile())
    val appointment by appointmentViewModel.appointment.observeAsState()

    var client by remember { mutableStateOf(Account()) }

    var address by remember { mutableStateOf(Address()) }

    var title by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var start by remember { mutableStateOf("") }
    var end by remember { mutableStateOf("") }

    var error by remember { mutableStateOf(mutableMapOf<String, String>())}

    val verticalScrollState = rememberScrollState()

    LaunchedEffect(key1 = clientProfile) {
            client = clientProfile!!.account
    }

//    LaunchedEffect(key1 = appointmentId) {
//        if (appointmentId != null && appointmentId != "new") {
//            appointmentViewModel.getAppointment(appointmentId)
//        }
//    }
//
    LaunchedEffect(key1 = appointment){
        if(appointment != null && appointmentId != "new") {
            client = appointment?.client!!
            title = appointment?.title!!
            notes = appointment?.notes!!
            date = getDate(appointment?.start!!)
            start = getTime(appointment?.start!!)
            end = getTime(appointment?.end!!)
            address = appointment?.address!!
        }
    }

    fun validate(): Boolean {
        var e = mutableMapOf<String, String>()
        if(client!!.id == "") {
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
                    address = address.copy(displayAddress = getAddressString(address)),
                    client = client, // BaseClient(client.id, Account(client.account.id, client.account.username)),
                    employee = user, // Account(appointment?.employee!!.id, appointment?.employee!!.username),
                    createBy = user, // Account(appointment?.createBy!!.id, appointment?.createBy!!.username),
                )
//                appointmentViewModel.updateAppointment(appointment?._id!!, data)
            } else {
                val data = Appointment(
                    _id = "",
                    title,
                    notes,
                    start = "$date $start",
                    end = "$date $end",
                    type = "sales",
                    address = address.copy(displayAddress = getAddressString(address)),
                    client = client, // BaseClient(client.id, Account(client.account.id, client.account.username)),
                    employee = user, // Account(user.id, user.username),
                    createBy = user, // Account(user.id, user.username),
                )
                appointmentViewModel.createAppointment(data);
            }

            // refresh list
            appointmentViewModel.getAppointmentsByEmployeeId(user.id!!)
            navController.navigate("appointments")
        }
    }

        AppointmentForm(
            navController,
            client,
            address,
            title,
            notes,
            date!!,
            start,
            end,
            onValChange = { name, value ->
                when (name) {
                    "title" -> title = value
                    "notes" -> notes = value
                    "date" -> date = value
                    "startTime" -> start = value
                    "endTime" -> end = value
                    "unitNumber" -> address = address.copy(unitNumber = value)
                    "streetNumber" -> address = address.copy(streetNumber = value)
                    "streetName" -> address = address.copy(streetName = value)
                    "city" -> address = address.copy(city = value)
                    "province" -> address = address.copy(province = value)
                    "country" -> address = address.copy(country = value)
                    "postcode" -> address = address.copy(postcode = value)
                }
            },
            onSubmit = ::handleSubmit,
            onCancel = {
                if(appointmentId!= "new"){
                    Log.d("zlk", "appointments/${appointmentId}")
                    navController.navigate("appointments/${appointmentId}")
                }else{
                    navController.navigate("appointments")
                }
            }
        )

}


//            when {
//                error.isNotEmpty() && error.containsKey("client") -> {
//                        Text(
//                            text = error["client"]!!,
//                            color = Color.Red,
//                            modifier = Modifier
//                                .padding(8.dp)
//                        )
//                }
//            }
//
//            Input(
//                value = title,
//                onValueChange = { title = it },
//                label = "Title",
//            )
//
//            Input(
//                value = notes,
//                onValueChange = { notes = it },
//                label = "Notes",
//            )
//
//            DatePicker(LocalContext.current, "Date", date, ::handleChangeDate)
//            when {
//                error.isNotEmpty() && error.containsKey("date") -> {
//                    Text(
//                        text = error["date"]!!,
//                        color = Color.Red,
//                        modifier = Modifier
//                            .padding(8.dp)
//                    )
//                }
//            }


//            Row(
//                modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)
//            ) {
//                Column(modifier = Modifier
//                    .weight(1f)
//                    .padding(end = 8.dp)
//                ) {
//                    TimePicker(
//                        LocalContext.current,
//                        "Start Time",
//                        time = start,
//                        onValueChange = { h, m -> start = getTime(h, m) },
//
//                    )
//                    when {
//                        error.isNotEmpty() && error.containsKey("start") -> {
//                            Text(
//                                text = error["start"]!!,
//                                color = Color.Red,
//                                modifier = Modifier
//                                    .padding(8.dp)
//                            )
//                        }
//                    }
//                }
//
//                Column(
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(start = 8.dp)
//                ) {
//                    TimePicker(
//                        LocalContext.current,
//                        "End Time",
//                        time = end,
//                        onValueChange = { h, m -> end = getTime(h, m) },
//                    )
//
//                    when {
//                        error.isNotEmpty() && error.containsKey("end") -> {
//                            Text(
//                                text = error["end"]!!,
//                                color = Color.Red,
//                                modifier = Modifier
//                                    .padding(8.dp)
//                            )
//                        }
//                    }
//                }
//            }

