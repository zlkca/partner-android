package com.artbird.onsite.ui.building

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.components.ActionChip
import com.artbird.onsite.ui.components.DropdownMenuItem
import com.artbird.onsite.ui.components.ListActionBar
import com.google.android.material.dialog.MaterialAlertDialogBuilder


@Composable
fun BuildingListScreen(
    navController: NavController,
    buildingViewModel: BuildingViewModel,
    appointment: Appointment2,
) {
    val buildings by buildingViewModel.buildings.observeAsState(listOf())
    var selectedBuilding by buildingViewModel.selectedBuilding

    var dialogOpened by remember { mutableStateOf(false) }
    var selectedBuildingIndex by remember { mutableStateOf(0) }

//    var status by remember { mutableStateOf(com.shutterlux.onsite.ui.building.ApiStatus.LOADING) }

    LaunchedEffect(key1 = appointment._id) {
        if (appointment._id != "" && appointment._id != "new") {
            buildingViewModel.getBuildingsByAppointmentId(appointment._id)
        }
    }

    fun handleSelectBuilding(index: Int) {

    }

    // dropdown menus
    fun handleEditBuilding(index: Int) {
        selectedBuildingIndex = index
        val building = buildings!![index]
        navController.navigate("buildings/${building._id}/form")
    }

    fun handleDeleteBuilding(index: Int){
        selectedBuildingIndex = index
        dialogOpened = true
    }

    val menus: List<DropdownMenuItem> = listOf(
        DropdownMenuItem("edit", "Edit", Icons.Outlined.Edit, "Edit", ::handleEditBuilding),
        DropdownMenuItem("delete", "Delete", Icons.Outlined.Delete, "Delete", ::handleDeleteBuilding),
    )

    fun getBuildingLabel(item: Building, name: String): String {
        return item.name
    }

    fun handleAddSample() {
        // create default building
        buildingViewModel.createBuildingSample(
            Building("",
                "New Address",
                "New building",
                appointmentId = appointment._id,
                floors = listOf(
                    Floor("", "First Floor", "", rooms = listOf(
                        Room("", "Living Room", ""),
                        Room("", "Family Room", ""),
                        Room("", "Dinning Room", ""),
                        Room("", "Kitchen", ""),
                    )),
                    Floor("", "Second Floor", "", rooms = listOf(
                        Room("", "Master Bedroom", ""),
                        Room("", "Room 1", ""),
                        Room("", "Room 2", ""),
                        Room("", "Room 3", ""),
                        Room("", "Washroom1", ""),
                        Room("", "Washroom2", ""),
                    )),
                )
            ),
        )
        buildingViewModel.getBuildingsByAppointmentId(appointment._id)
    }


//            if(status === com.shutterlux.onsite.ui.building.ApiStatus.DELETE_BUILDING_DONE) {


//            Toast.makeText(
//                LocalContext.current,
//                "Delete Building successfully",
//                Toast.LENGTH_SHORT,
//            ).show()
//    } else if(status == com.shutterlux.onsite.ui.building.ApiStatus.DELETE_BUILDING_ERROR) {
//        Toast.makeText(
//            LocalContext.current,
//            "Delete Building failed",
//            Toast.LENGTH_SHORT,
//        ).show()
//    }

    if(dialogOpened) {
        MaterialAlertDialogBuilder(LocalContext.current)
            .setTitle("Delete Building")
            .setMessage("Delete this building will remove floors, rooms, windows and corresponding measure data in this building. Are you sure to delete?")
            .setNegativeButton("Cancel") {dialog, which ->
                dialogOpened = false
                dialog.dismiss()
            }
            .setPositiveButton("Delete") {dialog, which ->
                val buildingId = buildings!![selectedBuildingIndex]._id
                if (buildingId != null) {
                    buildingViewModel.deleteBuilding(buildingId!!, appointment._id)
                    selectedBuildingIndex = 0
                }
                dialogOpened = false
                dialog.dismiss()
            }
            .show()
    }

    if (appointment._id == "" || appointment._id == "new"){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Please select an appointment")
        }
    }else {
        BuildingList(
            buildings!!,
            selectedIndex = selectedBuildingIndex,
            onSelect = { index ->
                selectedBuildingIndex = index
                val building = buildings!![index]
                selectedBuilding = building
                navController.navigate("buildings/${building._id}")
            },
            onAdd = { navController.navigate("buildings/new/form") }
        )
//        Column(
//            modifier = Modifier
//                .padding(8.dp)
//        ) {
//
////            Text(text = getAddressString(client.address), modifier = Modifier.padding(8.dp))
//
//            ListActionBar(items = listOf(
//                ActionChip("Building", onClick = { navController.navigate("buildings/new/form") }),
//                ActionChip("Sample Building", onClick = ::handleAddSample)
//            ))
//
//            if (buildings != null && buildings?.isNotEmpty()!!) {
//                com.artbird.onsite.ui.components.List<Building>(
//                    buildings!!,
//                    selectedBuildingIndex,
//                    fields = listOf("name"),
//                    onGetLabel = ::getBuildingLabel,
//                    onSelect = ::handleSelectBuilding,
//                    onSelectMenu = { index -> selectedBuildingIndex = index },
//                    menus = menus,
//                )
//            }
//        }
    }
}