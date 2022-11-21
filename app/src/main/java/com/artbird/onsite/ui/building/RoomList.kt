package com.artbird.onsite.ui.building

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.artbird.onsite.domain.Room
import com.artbird.onsite.ui.components.ActionButton

@Composable
fun RoomList(
//    navController: NavController,
    rooms: List<Room>,
    onAdd: (roomId: String) -> Unit,
    onEdit: (roomId: String) -> Unit,
    onDelete: (roomId: String) -> Unit,
    onView: (roomId: String) -> Unit,
){


    var selectedRoomIndex by remember { mutableStateOf(0) }

    // toolbar handler
    fun handleAdd(){

    }

    fun handleEdit(){
    }

    fun handleDelete(){
    }

    fun handleView(){
        val roomId = rooms[selectedRoomIndex]._id
        onView(roomId)
    }

    fun selectRoom(index: Int){
        selectedRoomIndex = index
    }

    fun getRoomLabel(item: Room, name: String): String {
        return item.name
    }

    Column() {
        Row() {
            ActionButton(Icons.Outlined.Visibility, "View", ::handleView)
            ActionButton(Icons.Outlined.Edit, "Edit", ::handleEdit)
            ActionButton(Icons.Outlined.Add, "Add", ::handleAdd)
            ActionButton(Icons.Outlined.Delete, "Delete", ::handleDelete)
        }

        com.artbird.onsite.ui.components.List<Room>(
            rooms,
            selectedRoomIndex,
            fields = listOf("name"),
            onGetLabel = ::getRoomLabel,
            onSelect = ::selectRoom,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRoomList(){
    val rooms = listOf(
        Room("", "Living Room", ""),
        Room("", "Family Room", ""),
        Room("", "Dinning Room", ""),
        Room("", "Breakfast", ""),
        Room("", "Kitchen", ""),
    )

    fun handleAdd(id: String){
    }

    fun handleEdit(id: String){
    }

    fun handleDelete(id: String){
    }

    fun handleView(id: String){
    }

    RoomList(rooms, ::handleAdd, ::handleEdit, ::handleDelete, ::handleView)
}

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