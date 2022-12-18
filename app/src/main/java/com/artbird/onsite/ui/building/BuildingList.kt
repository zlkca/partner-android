package com.artbird.onsite.ui.building

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.client.AccountListItem
import com.artbird.onsite.ui.client.ClientForm
import com.artbird.onsite.ui.components.*
import com.artbird.onsite.ui.theme.SLTheme

@Composable
fun BuildingListItem(item: Building, selected: Boolean, index:Int){
    val colorScheme = MaterialTheme.colorScheme
    Column(){
        Title2(
            text = item.name,
            color = if (selected) colorScheme.onPrimary else colorScheme.onBackground
        )

        Body3(
            text = item.notes,
            color = if (selected) colorScheme.onPrimary else colorScheme.onBackground,
        )
    }
}

@Composable
fun BuildingList(
    appointment: Appointment2,
    buildings: List<Building>,
    onSelect: (index: Int) -> Unit = { i: Int -> },
    onAdd: ()-> Unit ={},
    onAddSample: ()-> Unit = {},
    selectedIndex: Int,
    onBack: () -> Unit = {},
){
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        DetailsViewActionBar(
            onBack = onBack,
            readOnly = true,
        )
        Body1(text = appointment!!.address.displayAddress)

        ListActionBar(items = listOf(
            ActionChip("Building", onClick = onAdd),
            ActionChip("Sample Building", onClick = onAddSample)
        ))

        if (buildings != null && buildings?.isNotEmpty()!!) {
            com.artbird.onsite.ui.components.List<Building>(
                buildings!!,
                selectedIndex,
                onSelect = onSelect,
                itemContent = { it, selected, index ->
                    BuildingListItem(item=it, selected=selected, index =index)
                }
            )
        }
    }
}

@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewBuildingList(){
    val appointment = Appointment2(
        "1",
        title="My first appointment",
        address= Address("2", "", "235", "Front St", "Toronto", "ON", "L3R 0C7",
            displayAddress="235 Front St, Toronto, ON"
        ),
        client = Account(username="rick", email="rick@shutter.ca", phone="123-456-7890"),
        employee = Account(username="sales", email="sales@shutterlux.ca", phone="123-456-7890"),
        start = "2022-11-16 16:00:00",
        end = "2022-11-16 16:30:00",
        type = "sales",
        notes = "This is a test notes",
        createBy = Account(username="sales", email="sales@shutterlux.ca", phone="123-456-7890"),
    );
    val buildings = listOf<Building>(
        Building(_id="1", name="Main House", notes="Two stories with walkout basement", appointmentId = "1",
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
        ),
        Building(_id="1", name="Garage", notes="one story garage", appointmentId = "2",
            floors = listOf(
                Floor("", "First Floor", "", rooms = listOf(
                    Room("", "Garage 1", ""),
                    Room("", "Garage 2", ""),
                )),
            ),
        ),
    )

//    var address = Address("2", "", "235", "Front St", "Toronto", "ON", "L3R 0C7")

    SLTheme {
        BuildingList(
            appointment,
            buildings,
            selectedIndex = 1
        )
    }
}