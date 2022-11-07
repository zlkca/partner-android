package com.artbird.onsite.ui.window

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.components.ActionButton


@Composable
fun WindowList(
//    navController: NavController,
    windows: List<Window>,
    onAdd: (id: String) -> Unit,
    onEdit: (id: String) -> Unit,
    onDelete: (id: String) -> Unit,
    onView: (id: String) -> Unit,
){
    var selectedIndex by remember { mutableStateOf(0) }

    // toolbar handler
    fun handleAdd(){

    }

    fun handleEdit(){
    }

    fun handleDelete(){
    }

    fun handleView(){
        val id = windows[selectedIndex]._id
        if (id != null) {
            onView(id)
        }
    }

    fun selectWindow(index: Int){
        selectedIndex = index
    }

    fun getWindowLabel(item: Window, name: String): String {
        return item.name
    }

    Column() {
        Row() {
            ActionButton(Icons.Outlined.Visibility, "View", ::handleView)
            ActionButton(Icons.Outlined.Edit, "Edit", ::handleEdit)
            ActionButton(Icons.Outlined.Add, "Add", ::handleAdd)
            ActionButton(Icons.Outlined.Delete, "Delete", ::handleDelete)
        }

        com.artbird.onsite.ui.components.List<Window>(
            windows,
            selectedIndex,
            fields = listOf("name"),
            onGetLabel = ::getWindowLabel,
            onSelect = ::selectWindow,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWindowList(){
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

    fun handleAdd(id: String){
    }

    fun handleEdit(id: String){
    }

    fun handleDelete(id: String){
    }

    fun handleView(id: String){
    }

    WindowList(windows, ::handleAdd, ::handleEdit, ::handleDelete, ::handleView)
}
