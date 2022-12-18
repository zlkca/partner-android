package com.artbird.onsite.ui.building

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.artbird.onsite.domain.Building
import com.artbird.onsite.domain.Floor
import com.artbird.onsite.domain.Room
import com.artbird.onsite.domain.Window
import com.artbird.onsite.ui.components.*
import com.artbird.onsite.ui.window.WindowViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder


@Composable
fun RoomDetailsScreen(
    navController: NavController,
    buildingViewModel: BuildingViewModel,
    windowViewModel: WindowViewModel,
    buildingId: String,
    floorId: String,
    roomId: String,
) {
    val building by buildingViewModel.building.observeAsState(Building())
    val windows by windowViewModel.windows.observeAsState(arrayListOf())

    var floor by remember { mutableStateOf(Floor("","","", listOf())) }
    var room by remember { mutableStateOf(Room("","","")) }

//    val status by windowViewModel.status.observeAsState()
//

//
//    var selectedWindowIndex by remember { mutableStateOf(0) }
//    var dialogOpened by remember { mutableStateOf(false) }

    // dropdown menus
//    fun handleEditWindow(index: Int) {
//        selectedWindowIndex = index
//        val windowId = windows[index]._id
//        navController.navigate("buildings/${buildingId}/floors/${floorId}/rooms/${roomId}/windows/${windowId}/form")
//    }
//
//    fun handleDeleteWindow(index: Int) {
//        selectedWindowIndex = index
//        dialogOpened = true
//    }
//    val menus: List<DropdownMenuItem> = listOf(
//        DropdownMenuItem("edit", "Edit", Icons.Outlined.Edit, "Edit", ::handleEditWindow),
//        DropdownMenuItem("delete", "Delete", Icons.Outlined.Delete, "Delete", ::handleDeleteWindow),
//    )
    LaunchedEffect(key1 = buildingId){
        if(buildingId != null && buildingId != "new") {
            buildingViewModel.getBuilding(buildingId)
        }
    }

    LaunchedEffect(key1 = building) {
        if (building != null && buildingId != "new") {
            floor = building!!.floors.find { it -> it._id == floorId}!!
            room = floor!!.rooms.find { it -> it._id == roomId}!!
        }
    }
//
    LaunchedEffect(key1 = roomId){
        if(roomId != null && roomId != "new") {
            windowViewModel.getWindowsByRoomId(roomId)
        }
    }

//    if(status === DELETE_DONE) {
//        Toast.makeText(
//            LocalContext.current,
//            "Delete Window successfully",
//            Toast.LENGTH_SHORT,
//        ).show()
//    } else if(status == DELETE_ERROR) {
//        Toast.makeText(
//            LocalContext.current,
//            "Delete Window failed",
//            Toast.LENGTH_SHORT,
//        ).show()
//    }

//    if(dialogOpened) {
//        MaterialAlertDialogBuilder(LocalContext.current)
//            .setTitle("Delete Window")
//            .setMessage("Delete this room will remove this window and its measure data. Are you sure to delete?")
//            .setNegativeButton("Cancel") {dialog, which ->
//                dialogOpened = false
//                dialog.dismiss()
//            }
//            .setPositiveButton("Delete") {dialog, which ->
//                var windowId = windows[selectedWindowIndex]._id
//                if (windowId != null) {
//                    windowViewModel.deleteWindow(windowId)
//                }
//                dialogOpened = false
//                windowViewModel.getWindowsByRoomId(roomId)
//                dialog.dismiss()
//            }
//            .show()
//    }

    RoomDetails(
        navController = navController,
        building = building,
        floor = floor,
        room = room,
        windows = windows,
        onSelectWindow = {w->
            Log.d("zlk", "buildings/${buildingId}/floors/${floorId}/rooms/${roomId}/windows/${w._id}/form")
            navController.navigate("buildings/${buildingId}/floors/${floorId}/rooms/${roomId}/windows/${w._id}/form")
        },
        onBack = {
            Log.d("zlk","Back from Room Details to floor details: buildings/${buildingId}/floors/${floorId}")
            navController.navigate("buildings/${buildingId}/floors/${floorId}")
        },
        onEdit = {navController.navigate("buildings/${buildingId}/floors/${floorId}/rooms/${roomId}/form") }
    )

//    Column(
//        modifier = Modifier
//            .padding(8.dp)
//    ) {
//
//        DetailsViewActionBar(
//            onBack = { navController.navigate("buildings/${buildingId}/floors/${floorId}") },
//            onEdit = { navController.navigate("buildings/${buildingId}/floors/${floorId}/rooms/${roomId}/form") },
//        )
//
//        Text(text = "${building!!.name}", modifier = Modifier.padding(8.dp))
//        Text(text = "${floor.name}", modifier = Modifier.padding(8.dp))
//        Text(text = "${room.name}", modifier = Modifier.padding(8.dp))
//        Text(text = "${room.notes}", modifier = Modifier.padding(8.dp))
//
//        ListActionBar(
//            items = listOf(
//                ActionChip("Window", onClick = {
//                     navController.navigate(
//                         "buildings/${buildingId}/floors/${floorId}/rooms/${roomId}/windows/new/form"
//                     )
//                }),
////                ActionChip("Sample Window", onClick = {})
//            ),
//            onBack = { navController.navigate("buildings/${buildingId}/floors/${floorId}") }
//        )
//
//        if (windows.isNotEmpty()) {
//
//            fun getWindowLabel(item: Window, name: String): String {
//                return item.name
//            }
//
//            Column() {
//
//                com.artbird.onsite.ui.components.List<Window>(
//                    windows,
//                    selectedWindowIndex,
//                    fields = listOf("name"),
//                    onGetLabel = ::getWindowLabel,
//                    onSelect = ::handleSelectWindow,
//                    menus = menus
//                )
//            }
////            WindowList(windows, ::handleAdd, ::handleEdit, ::handleDelete, ::handleView)
//        }
//
//
//    }
}