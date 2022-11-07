package com.artbird.onsite.ui.building

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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.artbird.onsite.domain.Floor
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.artbird.onsite.ui.components.ActionChip
import com.artbird.onsite.ui.components.DetailsViewActionBar
import com.artbird.onsite.ui.components.DropdownMenuItem
import com.artbird.onsite.ui.components.ListActionBar

@Composable
fun BuildingDetailsScreen(
    navController: NavController,
    buildingViewModel: BuildingViewModel,
    buildingId: String?,
) {
    val building by buildingViewModel.building.observeAsState()
    val status by buildingViewModel.floorStatus.observeAsState()
    var floors: List<Floor> by remember { mutableStateOf(arrayListOf()) }
    var selectedFloorIndex by remember { mutableStateOf(0) }
    var dialogOpened by remember { mutableStateOf(false) }
//    var status by remember { mutableStateOf(com.shutterlux.onsite.ui.building.ApiStatus.LOADING) }

    LaunchedEffect(key1 = buildingId) {
        if (buildingId != null) {
            buildingViewModel.getBuilding(buildingId)
        }
    }

    LaunchedEffect(key1 = building) {
        if (building != null) {
            floors = building!!.floors
        }
    }



    fun handleSelectFloor(index: Int){
        selectedFloorIndex = index
        val floorId = floors[index]._id;
        navController.navigate("buildings/${buildingId}/floors/${floorId}");
    }

    // dropdown menus
    fun handleEditFloor(index: Int) {
        selectedFloorIndex = index
        val floor = floors!![index]
        navController.navigate("buildings/${buildingId}/floors/${floor._id}/form")
    }

    fun handleDeleteFloor(index: Int) {
        selectedFloorIndex = index
        dialogOpened = true
    }

    val menus: List<DropdownMenuItem> = listOf(
        DropdownMenuItem("edit", "Edit", Icons.Outlined.Edit, "Edit", ::handleEditFloor),
        DropdownMenuItem("delete", "Delete", Icons.Outlined.Delete, "Delete", ::handleDeleteFloor),
    )

    fun getFloorLabel(item: Floor, name: String): String {
        return item.name
    }

//    if(status === com.shutterlux.onsite.ui.building.ApiStatus.DELETE_FLOOR_DONE) {
//        Toast.makeText(
//            LocalContext.current,
//            "Delete Floor successfully",
//            Toast.LENGTH_SHORT,
//        ).show()
//    } else if(status == com.shutterlux.onsite.ui.building.ApiStatus.DELETE_FLOOR_ERROR) {
//        Toast.makeText(
//            LocalContext.current,
//            "Delete Floor failed",
//            Toast.LENGTH_SHORT,
//        ).show()
//    }

    if(dialogOpened) {
        MaterialAlertDialogBuilder(LocalContext.current)
            .setTitle("Delete Floor")
            .setMessage("Delete this floor will remove rooms, windows and corresponding measure data in this floor. Are you sure to delete?")
            .setNegativeButton("Cancel") {dialog, which ->
                dialogOpened = false
                dialog.dismiss()
            }
            .setPositiveButton("Delete") {dialog, which ->
                val floorId = floors[selectedFloorIndex]._id
                if (floorId != null) {
                    buildingViewModel.deleteFloor(floorId, buildingId!!)
                    selectedFloorIndex = 0
                }
                dialogOpened = false
                dialog.dismiss()
            }
            .show()
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        if(building!=null) {
            DetailsViewActionBar(
                onBack = { navController.navigate("buildings") },
                onEdit = { navController.navigate("buildings/${buildingId}/form") },
            )
            Text(text = "${building!!.name}", fontSize=16.sp, modifier = Modifier.padding(8.dp))
            Text(text = "${building!!.notes}", fontSize=12.sp, modifier = Modifier.padding(8.dp))
        }

        if(floors != null) {
            ListActionBar(items = listOf(
                ActionChip("Floor", onClick = { navController.navigate("buildings/${buildingId}/floors/new/form")}),
//                ActionChip("Sample Floor", onClick = {})
            ))

            if (floors?.isNotEmpty()!!) {
                com.artbird.onsite.ui.components.List<Floor>(
                    floors!!,
                    selectedFloorIndex,
                    fields = listOf("name"),
                    onGetLabel = ::getFloorLabel,
                    onSelect = ::handleSelectFloor,
                    onSelectMenu = { it -> selectedFloorIndex = it},
                    menus = menus
                )
            }
        }

    }

}
