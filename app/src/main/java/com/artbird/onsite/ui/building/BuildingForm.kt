package com.artbird.onsite.ui.building

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.artbird.onsite.domain.BaseAppointment
import com.artbird.onsite.domain.Building
import com.artbird.onsite.domain.Floor
import com.artbird.onsite.domain.Room
import com.artbird.onsite.ui.components.FormActionBar
import com.artbird.onsite.ui.components.Input
import com.artbird.onsite.ui.theme.SLTheme

@Composable
fun BuildingForm(
    navController: NavController,
    building: Building,
    error: Map<String, String> = mapOf<String, String>(),
    onChange: (fieldName: String, value: String) -> Unit = {f,v -> },
    onSave: ()-> Unit = {}
){
    Column(modifier = Modifier
        .padding(8.dp)
//        .verticalScroll(verticalScrollState)
    ) {

        FormActionBar(
            onCancel = {
                navController.navigate("buildings")
            },
            onSave = onSave
        )

        Input(
            value = building.name,
            onValueChange = { onChange("name", it) },
            label = "Building Name",
        )

        Input(
            value = building.notes,
            onValueChange = { onChange("notes", it) },
            label = "Notes",
        )
    }
}

@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewBuildingForm(){
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
        BuildingForm(
            rememberNavController(),
            building
        )
    }
}