package com.artbird.onsite.ui.building

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.Room
import com.artbird.onsite.domain.Window
import com.artbird.onsite.ui.components.Input


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomForm(
    room: Room?,
    onSubmit: () -> Unit = {},
){

    var name by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    val verticalScrollState = rememberScrollState()


    fun handleSubmit(){

    }

    var selectedIndex by remember { mutableStateOf(0) }

    fun selectWindow(index: Int){
        selectedIndex = index
    }

    fun getWindowLabel(item: Window, name: String): String {
        return item.name
    }

//    fun handleDelete(){
//    }

        Column(modifier = Modifier
            .padding(12.dp)
            .verticalScroll(verticalScrollState)) {

            if(room !== null) {

                Input(
                    value = name,
                    onValueChange = { name = it },
                    label = "Room Name",
                )

                Input(
                    value = notes,
                    onValueChange = { notes = it },
                    label = "Notes",
                )
            }
        }
}