package com.artbird.onsite.ui.building

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.artbird.onsite.domain.Appointment
import com.artbird.onsite.domain.Building
import com.artbird.onsite.domain.Floor
import com.artbird.onsite.ui.components.ActionButton
import com.artbird.onsite.ui.components.DatePicker
import com.artbird.onsite.ui.components.Input
import com.artbird.onsite.ui.components.TimePicker
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuildingForm(
    building: Building?,
    onSubmit: () -> Unit,
){

    var name by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    val verticalScrollState = rememberScrollState()


    fun handleSubmit(){

    }

    var selectedIndex by remember { mutableStateOf(0) }

    fun selectFloor(index: Int){
        selectedIndex = index
    }

    fun getFloorLabel(item: Floor, name: String): String {
        return item.name
    }


        Column(modifier = Modifier
            .padding(12.dp)
            .verticalScroll(verticalScrollState)) {
            if(building !== null) {

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
}