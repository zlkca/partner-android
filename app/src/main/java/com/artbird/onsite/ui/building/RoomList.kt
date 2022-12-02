package com.artbird.onsite.ui.building

import androidx.compose.foundation.layout.Column
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.artbird.onsite.domain.Room
import android.content.res.Configuration
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.artbird.onsite.ui.components.ActionChip
import com.artbird.onsite.ui.components.Body3
import com.artbird.onsite.ui.components.ListActionBar
import com.artbird.onsite.ui.components.Title2
import com.artbird.onsite.ui.theme.SLTheme

@Composable
fun RoomListItem(item: Room, selected: Boolean, index:Int){
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
fun RoomList(
    rooms: List<Room>,
    onSelect: (index: Int) -> Unit = { i: Int -> },
    onAddRoom: ()-> Unit = {},
    onAddSample: ()-> Unit = {},
    selectedIndex: Int,
){
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        ListActionBar(items = listOf(
            ActionChip("Room", onClick = onAddRoom),
            ActionChip("Sample Room", onClick = onAddSample)
        ))

        if (rooms != null && rooms?.isNotEmpty()!!) {
            com.artbird.onsite.ui.components.List<Room>(
                rooms!!,
                selectedIndex,
                onSelect = onSelect,
                itemContent = { it, selected, index ->
                    RoomListItem(item=it, selected=selected, index =index)
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
fun PreviewRoomList(){
    val rooms = listOf(
                Room("", "Living Room", ""),
                Room("", "Family Room", ""),
                Room("", "Dinning Room", ""),
                Room("", "Kitchen", ""),
            )
//    var address = Address("2", "", "235", "Front St", "Toronto", "ON", "L3R 0C7")

    SLTheme {
        RoomList(
            rooms,
            selectedIndex = 1
        )
    }
}
//
//@Composable
//fun RoomList(
////    navController: NavController,
//    rooms: List<Room>,
//    onAdd: (roomId: String) -> Unit,
//    onEdit: (roomId: String) -> Unit,
//    onDelete: (roomId: String) -> Unit,
//    onView: (roomId: String) -> Unit,
//){
//
//
//    var selectedRoomIndex by remember { mutableStateOf(0) }
//
//    // toolbar handler
//    fun handleAdd(){
//
//    }
//
//    fun handleEdit(){
//    }
//
//    fun handleDelete(){
//    }
//
//    fun handleView(){
//        val roomId = rooms[selectedRoomIndex]._id
//        onView(roomId)
//    }
//
//    fun selectRoom(index: Int){
//        selectedRoomIndex = index
//    }
//
//    fun getRoomLabel(item: Room, name: String): String {
//        return item.name
//    }
//
//    Column() {
//        Row() {
//            ActionButton(Icons.Outlined.Visibility, "View", ::handleView)
//            ActionButton(Icons.Outlined.Edit, "Edit", ::handleEdit)
//            ActionButton(Icons.Outlined.Add, "Add", ::handleAdd)
//            ActionButton(Icons.Outlined.Delete, "Delete", ::handleDelete)
//        }
//
//        com.artbird.onsite.ui.components.List<Room>(
//            rooms,
//            selectedRoomIndex,
//            fields = listOf("name"),
//            onGetLabel = ::getRoomLabel,
//            onSelect = ::selectRoom,
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewRoomList(){
//    val rooms = listOf(
//        Room("", "Living Room", ""),
//        Room("", "Family Room", ""),
//        Room("", "Dinning Room", ""),
//        Room("", "Breakfast", ""),
//        Room("", "Kitchen", ""),
//    )
//
//    fun handleAdd(id: String){
//    }
//
//    fun handleEdit(id: String){
//    }
//
//    fun handleDelete(id: String){
//    }
//
//    fun handleView(id: String){
//    }
//
//    RoomList(rooms, ::handleAdd, ::handleEdit, ::handleDelete, ::handleView)
//}

//@Composable
//fun RoomListItem(item: RoomListItem, index: Int, selectedIndex: Int){
//    Row(
//        modifier = Modifier
//            .background(
//                color = if (selectedIndex == index) Color.Gray else Color.Transparent
//            )
//            .clickable { item.onClick(index) }
//    ) {
//        Box(modifier = Modifier
//            .padding(8.dp)
//            .weight(1f)) {
//            Text(text = item.name, color = if (selectedIndex == index) Color.White else Color.Black)
//        }
////        Box(modifier = Modifier
////            .padding(8.dp)
////            .weight(1f)) {
////            Text(text = item.status, color = if (selectedIndex == index) Color.White else Color.Black)
////        }
//    }
//}