package com.artbird.onsite.ui.building

import com.artbird.onsite.ui.window.WindowList


import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.components.ActionChip
import com.artbird.onsite.ui.components.Body1
import com.artbird.onsite.ui.components.DetailsViewActionBar
import com.artbird.onsite.ui.components.ListActionBar
import com.artbird.onsite.ui.theme.SLTheme

@Composable
fun RoomDetails(
    navController: NavController,
    building: Building,
    floor: Floor,
    room: Room,
    windows: List<Window>,
    onSelectWindow: (w: Window) -> Unit = { w: Window -> },
    onBack: () -> Unit = {},
    onEdit: () -> Unit = {},
){
//    var windows: List<Window> by remember { mutableStateOf(arrayListOf()) }
    var selectedRoomIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        if(room!=null) {
            DetailsViewActionBar(
                onBack = onBack,
                onEdit = onEdit,
            )
            Body1(text = building!!.name)
            Body1(text = floor!!.name)
            Body1(text = "${room!!.name}")
            Body1(text = "${room!!.notes}")
        }

        WindowList(
            windows,
            selectedIndex = selectedRoomIndex,
            onSelect = { index ->
                onSelectWindow(windows[index]);
            },
            onAddSample = {},
            onAdd = {
                navController.navigate("buildings/${building._id}/floors/${floor._id}/rooms/${room._id}/windows/new/form")
            }
        )
    }
}

@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewRoomDetails(){
    val room = Room("", "First Room", "")

    val building = Building(_id="1", name="Main House", notes="Two stories with walkout basement", appointmentId = "",
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

    val floor = Floor("", "First Floor", "", rooms = listOf(
    Room("", "Living Room", ""),
    Room("", "Family Room", ""),
    Room("", "Dinning Room", ""),
    Room("", "Kitchen", ""),
    ))

    val windows = listOf(
        Window("", "Window 1", "",
            WindowWidth(0, 0, 0),
            WindowHeight(0,0,0),
            type ="Normal",
            "L",
            "L",
            1,
            mountPosition = "Inside",
            BafflePosition(0,0),
            "",
            "",
            frameStyle = "L Frame",
            originalFrameStyle = "Normal",
            lockers = listOf<Locker>(),
            room = BaseEntity("", ""),
            appointment = BaseAppointment("", "title")
        ),
        Window("", "Window 2", "",
            WindowWidth(0, 0, 0),
            WindowHeight(0,0,0),
            "Normal",
            "L",
            "L",
            1,
            mountPosition = "Inside",
            BafflePosition(0,0),
            "",
            "",
            frameStyle = "L Frame",
            originalFrameStyle = "Normal",
            lockers = listOf<Locker>(),
            room = BaseEntity("", ""),
            appointment = BaseAppointment("", "title")
        ),
    )

    SLTheme {
        RoomDetails(
            rememberNavController(),
            building,
            floor,
            room,
            windows
        )
    }
}