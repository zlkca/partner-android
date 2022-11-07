package com.artbird.onsite.ui.floor


//import android.util.Log
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.icons.outlined.Add
//import androidx.compose.material.icons.outlined.Delete
//import androidx.compose.material.icons.outlined.Edit
//import androidx.compose.material.icons.outlined.Visibility
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.CalendarToday
//import androidx.compose.material.icons.filled.Dashboard
//import androidx.compose.material.icons.outlined.Add
//import androidx.compose.material.icons.outlined.Delete
//import androidx.compose.material.icons.outlined.Edit
//import androidx.compose.material.icons.outlined.Visibility

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.building.FloorForm
import com.artbird.onsite.ui.building.RoomForm

//import androidx.navigation.compose.rememberNavController
//import com.shutterlux.onsite.domain.RoomListItem
import com.artbird.onsite.ui.components.*
//import com.shutterlux.onsite.ui.components.List

@Composable
fun FloorDetailsView(
    building: Building,
    floorIndex: Int,
    floor: Floor,
    onNext: (step: String, index: Int) -> Unit,
    onPrevious: (step: String, index: Int) -> Unit
) {

//    val building by buildingViewModel.building.observeAsState()
////    val quotes by quoteViewModel.quotes.observeAsState()
//
//    LaunchedEffect(key1 = buildingId){
//        if(buildingId != null) {
//            buildingViewModel.getBuilding(buildingId)
//        }
//    }
    var room by remember { mutableStateOf(Room("","","")) } // add new
    var mode by remember { mutableStateOf("view") }
    var roomIndex by remember { mutableStateOf(0) }

    var page by remember { mutableStateOf("floor-view") }

    fun handleSelect(idx: Int) {
        roomIndex = idx
    }

    fun selectRoom(index: Int) {
        roomIndex = index
        onNext("windows", roomIndex)
    }

    fun getRoomLabel(item: Room, name: String): String {
        return item.name
    }


    // toolbar handler
    fun handleAdd(type: String) {
        mode = "edit"
        if (type == "room") {
            page = "room-form"
        }
    }

    fun handleDelete() {

    }

    fun handleView() {
//        val id = building?.rooms?.get(roomIndex)?._id
//        if (id != null) {
//            navController.navigate("buildings/${id}/rooms/${roomIndex}")
//        }
    }

    fun handleBack() {
        onPrevious("floors", floorIndex)
    }

    fun handleSubmit() {
        mode = "view"
    }

    fun handleEdit() {
        mode = "edit"
    }

    fun handleCancel() {
        mode = "view"
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {

        if (page == "floor-view") {
            var rooms = floor.rooms

            if (rooms != null) {

                OldFormActionBar(mode,
                    onCancel = ::handleCancel,
                    onBack = ::handleBack,
                    onSave = ::handleSubmit,
                    onEdit = ::handleEdit)

                if (mode == "edit") {
                    FloorForm(floor, ::handleSubmit)
                } else {
                    Text(text = "${building.name}", modifier = Modifier.padding(8.dp))
                    Text(text = "${floor.name}", modifier = Modifier.padding(8.dp))
                    Text(text = "${floor.notes}", modifier = Modifier.padding(8.dp))


                    ListActionBar(items = listOf(
                        ActionChip("Room", onClick = {
                            handleAdd("room")
                        }),
                        ActionChip("Sample Room", onClick = {
                            handleAdd("sample-room")
                        })
                    ))

                    if (rooms != null && rooms?.isNotEmpty()!!) {
                        com.artbird.onsite.ui.components.List<Room>(
                            rooms!!,
                            roomIndex,
                            fields = listOf("name"),
                            onGetLabel = ::getRoomLabel,
                            onSelect = ::selectRoom,
                        )
                    }
                }
            }
        } else { // page == "room-form" for add new room
            Column() {
                FormActionBar(
                    onCancel = {onPrevious("rooms", roomIndex)},
                    onSave = ::handleSubmit,
                    )
                RoomForm(room)
            }
        }
    }


}



