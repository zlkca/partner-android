package com.artbird.onsite.ui.floor

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
import com.artbird.onsite.domain.BaseAppointment
import com.artbird.onsite.domain.Floor
import com.artbird.onsite.domain.Room
import com.artbird.onsite.ui.components.ActionChip
import com.artbird.onsite.ui.components.Body3
import com.artbird.onsite.ui.components.ListActionBar
import com.artbird.onsite.ui.components.Title2
import com.artbird.onsite.ui.theme.SLTheme

@Composable
fun FloorListItem(item: Floor, selected: Boolean, index:Int){
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
fun FloorList(
    buildingId: String,
    floors: List<Floor>,
    onSelect: (index: Int) -> Unit = { i: Int -> },
    onAdd: ()-> Unit = {},
    onAddSample: ()-> Unit = {},
    selectedIndex: Int,
){
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        ListActionBar(items = listOf(
            ActionChip("Floor", onClick = onAdd),
            ActionChip("Sample Floor", onClick = onAddSample)
        ))

        if (floors != null && floors?.isNotEmpty()!!) {
            com.artbird.onsite.ui.components.List<Floor>(
                floors!!,
                selectedIndex,
                onSelect = onSelect,
                itemContent = { it, selected, index ->
                    FloorListItem(item=it, selected=selected, index =index)
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
fun PreviewFloorList(){
    val floors = listOf<Floor>(
        Floor(_id="1", name="First floor", notes="My first floor",
            rooms = listOf(
                    Room("", "Living Room", ""),
                    Room("", "Family Room", ""),
                    Room("", "Dinning Room", ""),
                    Room("", "Kitchen", ""),
                )
            ),
        Floor(_id="2", name="Second floor", notes="My second floor",
            rooms = listOf(
                Room("", "Living Room", ""),
                Room("", "Family Room", ""),
                Room("", "Dinning Room", ""),
                Room("", "Kitchen", ""),
            )
        ),
    )

//    var address = Address("2", "", "235", "Front St", "Toronto", "ON", "L3R 0C7")

    var buildingId = "12"

    SLTheme {
        FloorList(
            buildingId,
            floors,
            selectedIndex = 1
        )
    }
}