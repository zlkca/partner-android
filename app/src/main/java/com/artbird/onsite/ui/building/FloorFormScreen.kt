package com.artbird.onsite.ui.building

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.artbird.onsite.domain.Building
import com.artbird.onsite.domain.Floor

@Composable
fun FloorFormScreen(
    navController: NavController,
    buildingViewModel: BuildingViewModel,
    buildingId: String?,
    floorId: String,
){
    var selectedFloor by buildingViewModel.selectedFloor
    val building by buildingViewModel.building.observeAsState(Building())

    var floor by remember {
        mutableStateOf(
            Floor()
        )
    }

    LaunchedEffect(key1 = buildingId) {
        if (buildingId != null && buildingId != "new") {
            buildingViewModel.getBuilding(buildingId)
        }
    }

    LaunchedEffect(key1 = selectedFloor) {
        if(floorId != "new"){
            floor = selectedFloor
        }
    }


    fun handleSubmit(){
        val mFloors = ArrayList<Floor>();
        if(floorId == "new") { // create new floor
            building.floors.forEach {
                mFloors.add(it);
            }
            mFloors.add(Floor("", floor.name, floor.notes, listOf()))
        }else{
            building.floors.forEach {
                if(it._id == floorId){
                    mFloors.add(Floor(it._id, floor.name, floor.notes, it.rooms));
                }else{
                    mFloors.add(it);
                }
            }
        }

        if (buildingId != null) {
            buildingViewModel.updateBuilding(
                buildingId,
                building.copy(
                    _id = "",
                    floors = mFloors
                )
            )
            // refresh list
            buildingViewModel.getBuilding(buildingId)
        }

        navController.navigate("buildings/${buildingId}")
    }


    FloorForm(
        floor,
        onChange = {f, value ->
            when(f){
                "name" -> floor = floor.copy(name = value)
                "notes" -> floor = floor.copy(notes = value)
            }
        },
        onSave = ::handleSubmit,
        onCancel = {
            navController.navigate("buildings/${buildingId}")
        }
    )
}