package com.artbird.onsite.ui.building

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.artbird.onsite.domain.BaseAppointment
import com.artbird.onsite.domain.Building
import com.artbird.onsite.domain.Floor
import com.artbird.onsite.domain.Room
import com.artbird.onsite.ui.components.ActionChip
import com.artbird.onsite.ui.components.Body1
import com.artbird.onsite.ui.components.DetailsViewActionBar
import com.artbird.onsite.ui.components.ListActionBar
import com.artbird.onsite.ui.floor.FloorListItem
import com.artbird.onsite.ui.theme.SLTheme

@Composable
fun BuildingDetails(
    navController: NavController,
    building: Building,
){
    var floors = building.floors; // : List<Floor> by remember { mutableStateOf(arrayListOf()) }
    var selectedFloorIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        if(building!=null) {
            DetailsViewActionBar(
                onBack = { navController.navigate("buildings") },
                onEdit = { navController.navigate("buildings/${building._id}/form") },
            )
            Body1(text = "${building!!.name}")
            Body1(text = "${building!!.notes}")
        }

        if(floors != null) {
            ListActionBar(items = listOf(
                ActionChip("Floor", onClick = { navController.navigate("buildings/${building._id}/floors/new/form")}),
//                ActionChip("Sample Floor", onClick = {})
            ))

            if (floors?.isNotEmpty()!!) {
//                com.artbird.onsite.ui.components.List<Floor>(
//                    floors!!,
//                    selectedFloorIndex,
////                    fields = listOf("name"),
////                    onGetLabel = ::getFloorLabel,
////                    onSelect = ::handleSelectFloor,
////                    onSelectMenu = { it -> selectedFloorIndex = it},
////                    menus = menus
//                )

                com.artbird.onsite.ui.components.List<Floor>(
                    floors!!,
                    selectedIndex = selectedFloorIndex,
                    onSelect = {},
                    itemContent = { it, selected, index ->
                        FloorListItem(item=it, selected=selected, index =index)
                    }
                )
            }
        }

    }
}

@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewBuildingDetails(){
    val building = Building(_id="1", name="Main House", notes="Two stories with walkout basement", appointment = BaseAppointment(),
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
        ),
    )

//    var address = Address("2", "", "235", "Front St", "Toronto", "ON", "L3R 0C7")

    SLTheme {
        BuildingDetails(
            rememberNavController(),
            building
        )
    }
}