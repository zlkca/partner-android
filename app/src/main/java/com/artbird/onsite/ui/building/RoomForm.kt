package com.artbird.onsite.ui.building

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.Floor
import com.artbird.onsite.domain.Room
import com.artbird.onsite.ui.components.FormActionBar
import com.artbird.onsite.ui.components.Input
import com.artbird.onsite.ui.theme.SLTheme


@Composable
fun RoomForm(
    room: Room,
    error: Map<String, String> = mapOf<String, String>(),
    onChange: (fieldName: String, value: String) -> Unit = {f,v -> },
    onSave: ()-> Unit = {},
    onCancel: ()-> Unit = {}
){
    Column(modifier = Modifier
        .padding(12.dp)
//        .verticalScroll(verticalScrollState)
    ) {
        FormActionBar(
            onCancel = onCancel,
            onSave = onSave
        )

        Input(
            value = room.name,
            onValueChange = { onChange("name", it) },
            label = "Room Name",
        )

        Input(
            value = room.notes,
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
fun PreviewRoomForm(){
    val room = Room("", "First Room", "",
//        rooms = listOf(
//            Room("", "Living Room", ""),
//            Room("", "Family Room", ""),
//            Room("", "Dinning Room", ""),
//            Room("", "Kitchen", ""),
//        )
        )

//    val building = Building()
//    var address = Address("2", "", "235", "Front St", "Toronto", "ON", "L3R 0C7")

    SLTheme {
        RoomForm(
            room
        )
    }
}