package com.artbird.onsite.ui.measure

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.BaseAppointment
import com.artbird.onsite.domain.Building
import com.artbird.onsite.domain.Floor
import com.artbird.onsite.domain.Room
import com.artbird.onsite.ui.building.BuildingForm
import com.artbird.onsite.ui.components.ActionChip
import com.artbird.onsite.ui.components.ListActionBar


@Composable
fun BuildingListView(
    buildings: List<Building>?,
    onNext: (step: String, index: Int) -> Unit = { s: String, i: Int -> },
    onPrevious: (step: String, index: Int) -> Unit = { s: String, i: Int -> },
    onDelete: (buildingId: String) -> Unit = {s: String ->},
    onAdd: (type: String) -> Unit = {},
) {

    var formOpened by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }

    // toolbar handler


    fun handleEdit() {
        formOpened = true
    }

    fun handleDelete(index: Int) {
        val buildingId = buildings?.get(index)?._id!!
        onDelete(buildingId)
    }

    fun handleView() {
    }

    fun selectBuilding(index: Int) {
        selectedIndex = index
        onNext("floors", selectedIndex)
    }

    fun getBuildingLabel(item: Building, name: String): String {
        return item.name
    }

    fun handleSubmit(){
        formOpened = false
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
//        Row() {
//            ActionButton(Icons.Outlined.Visibility, "View", ::handleView)
//            ActionButton(Icons.Outlined.Edit, "Edit", ::handleEdit)
//            ActionButton(Icons.Outlined.Add, "Add", ::handleAdd)
//            ActionButton(Icons.Outlined.Delete, "Delete", ::handleDelete)
//        }

        ListActionBar(items = listOf(
            ActionChip("Building", onClick = {onAdd("normal")}),
            ActionChip("Sample Building", onClick = {onAdd("sample")})
        ))

        if (buildings != null && buildings?.isNotEmpty()!!) {
            com.artbird.onsite.ui.components.List<Building>(
                buildings!!,
                selectedIndex,
                fields = listOf("name"),
                onGetLabel = ::getBuildingLabel,
                onSelect = ::selectBuilding,
//                onHighlight = {index -> selectedIndex = index},
//                onDelete = ::handleDelete
            )

            if(formOpened) {
//                BuildingForm(buildings!![selectedIndex], ::handleSubmit)
            }
        }


    }

}