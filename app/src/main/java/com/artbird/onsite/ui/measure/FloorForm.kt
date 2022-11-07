package com.artbird.onsite.ui.building

import com.artbird.onsite.domain.Room

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.Floor
import com.artbird.onsite.ui.components.Input

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloorForm(
    floor: Floor?,
    onSubmit: () -> Unit = {},
){

    var name by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    val verticalScrollState = rememberScrollState()


    fun getRoomLabel(item: Room, name: String): String {
        return item.name
    }

//    fun handleDelete(){
//    }
        Column(modifier = Modifier
            .padding(12.dp)
            .verticalScroll(verticalScrollState)) {
            if(floor !== null) {

                Input(
                    value = name,
                    onValueChange = { name = it },
                    label = "Floor Name",
                )

                Input(
                    value = notes,
                    onValueChange = { notes = it },
                    label = "Notes",
                )

            }
        }
}