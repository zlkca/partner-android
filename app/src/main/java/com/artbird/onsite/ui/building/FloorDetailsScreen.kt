package com.artbird.onsite.ui.building

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext

import androidx.navigation.NavController
import com.artbird.onsite.domain.Building
import com.artbird.onsite.domain.Floor
import com.artbird.onsite.domain.Room
import com.artbird.onsite.ui.components.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder


@Composable
fun FloorDetailsScreen(
    navController: NavController,
    buildingViewModel: BuildingViewModel,
    buildingId: String,
    floorId: String,
) {

    val building by buildingViewModel.building.observeAsState(Building())
//    val status by buildingViewModel.status.observeAsState()

    var floor by remember { mutableStateOf(Floor("","","", listOf())) }
    var dialogOpened by remember { mutableStateOf(false) }
    var rooms: List<Room> by remember { mutableStateOf(listOf()) }

    LaunchedEffect(key1 = buildingId) {
        if (buildingId != null && buildingId != "new") {
            buildingViewModel.getBuilding(buildingId)
        }
    }

    LaunchedEffect(key1 = building) {
        if (building != null) {
            if(building!!.floors.isNotEmpty()){
                floor = building!!.floors.find {it._id == floorId}!!
                rooms = floor.rooms
            }
        }
    }

    var selectedRoomIndex by remember { mutableStateOf(0) }

    fun handleSelectRoom(index: Int) {
        selectedRoomIndex = index
        val room = floor!!.rooms[index];
        if (room._id != "" && room._id != "new") {
            navController.navigate("rooms/${room._id}")
        }
    }

    // dropdown menus
    fun handleEditRoom(index: Int) {
        selectedRoomIndex = index
        val room = floor!!.rooms!![index]
        navController.navigate("rooms/${room._id}/form")
    }

    fun handleDeleteRoom(index: Int) {
        selectedRoomIndex = index
        dialogOpened = true
    }

    val menus: List<DropdownMenuItem> = listOf(
        DropdownMenuItem("edit", "Edit", Icons.Outlined.Edit, "Edit", ::handleEditRoom),
        DropdownMenuItem("delete", "Delete", Icons.Outlined.Delete, "Delete", ::handleDeleteRoom),
    )

    fun getRoomLabel(item: Room, name: String): String {
        return item.name
    }

//    if(status === com.shutterlux.onsite.ui.building.ApiStatus.DELETE_ROOM_DONE) {
//        Toast.makeText(
//            LocalContext.current,
//            "Delete Room successfully",
//            Toast.LENGTH_SHORT,
//        ).show()
//    } else if(status == com.shutterlux.onsite.ui.building.ApiStatus.DELETE_ROOM_ERROR) {
//        Toast.makeText(
//            LocalContext.current,
//            "Delete Room failed",
//            Toast.LENGTH_SHORT,
//        ).show()
//    }

    if(dialogOpened) {
        MaterialAlertDialogBuilder(LocalContext.current)
            .setTitle("Delete Room")
            .setMessage("Delete this room will remove windows and corresponding measure data in this room. Are you sure to delete?")
            .setNegativeButton("Cancel") {dialog, which ->
                dialogOpened = false
                dialog.dismiss()
            }
            .setPositiveButton("Delete") {dialog, which ->
                var roomId = floor.rooms[selectedRoomIndex]._id
                if (roomId != null) {
                    // buildingViewModel.deleteRoom(roomId, buildingId, floorId)
                    selectedRoomIndex = 0
                }
                dialogOpened = false
                dialog.dismiss()
            }
            .show()
    }

    FloorDetails(
        navController = navController,
        building = building,
        floor = floor,
        onSelectRoom = {
            Log.d("zlk", "To Room details screen: buildings/${buildingId}/floors/${floorId}/rooms/${it._id}")
            navController.navigate("buildings/${buildingId}/floors/${floorId}/rooms/${it._id}")
        },
        onBack = {
            Log.d("zlk","Back from Floor Details to Building details: buildings/${buildingId}/floors/${floorId}")
            navController.navigate("buildings/${buildingId}")
        },
        onEdit = { navController.navigate("buildings/${buildingId}/floors/${floor._id}/form") },
    )
//    Column(
//        modifier = Modifier.padding(8.dp)
//    ) {
//
//        if(building!= null && floor!=null) {
//            DetailsViewActionBar(
//                onBack = { navController.navigate("buildings/${buildingId}") },
//                onEdit = { navController.navigate("buildings/${buildingId}/floors/${floorId}/form") },
//            )
//            Text(text = "${building!!.name}", modifier = Modifier.padding(8.dp))
//            Text(text = "${floor.name}", modifier = Modifier.padding(8.dp))
//            Text(text = "${floor.notes}", modifier = Modifier.padding(8.dp))
//        }
//
//        if (floor != null) {
//            ListActionBar(
//                items = listOf(
//                    ActionChip("Room",
//                        onClick = { navController.navigate("buildings/${buildingId}/floors/${floorId}/rooms/new/form") }),
////                    ActionChip("Sample Room", onClick = {})
//                ),
//                onBack = { navController.navigate("buildings/${buildingId}") }
//            )
//
//            if (rooms != null && rooms?.isNotEmpty()!!) {
//                com.artbird.onsite.ui.components.List<Room>(
//                    rooms!!,
//                    selectedRoomIndex,
//                    fields = listOf("name"),
//                    onGetLabel = ::getRoomLabel,
//                    onSelect = ::handleSelectRoom,
//                    onSelectMenu = { it -> selectedRoomIndex = it},
//                    menus = menus
//                )
//            }
//        }
//    }

}


