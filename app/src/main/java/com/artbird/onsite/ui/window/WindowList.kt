package com.artbird.onsite.ui.window

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.building.RoomList
import com.artbird.onsite.ui.building.RoomListItem
import com.artbird.onsite.ui.components.*
import com.artbird.onsite.ui.theme.SLTheme

@Composable
fun WindowListItem(item: Window, selected: Boolean, index:Int){
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
fun WindowList(
    windows: List<Window>,
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
            ActionChip("Window", onClick = onAdd),
            ActionChip("Sample Window", onClick = onAddSample)
        ))

        if (windows != null && windows?.isNotEmpty()!!) {
            com.artbird.onsite.ui.components.List<Window>(
                windows!!,
                selectedIndex,
                onSelect = onSelect,
                itemContent = { it, selected, index ->
                    WindowListItem(item=it, selected=selected, index =index)
                }
            )
        }
    }
}
//
//@Composable
//fun WindowList2(
//    navController: NavController,
//    windows: List<Window>,
//    onAdd: (id: String) -> Unit,
//    onEdit: (id: String) -> Unit,
//    onDelete: (id: String) -> Unit,
//    onView: (id: String) -> Unit,
//){
//    var selectedIndex by remember { mutableStateOf(0) }
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
//        val id = windows[selectedIndex]._id
//        if (id != null) {
//            onView(id)
//        }
//    }
//
//    fun selectWindow(index: Int){
//        selectedIndex = index
//    }
//
//    fun getWindowLabel(item: Window, name: String): String {
//        return item.name
//    }
//
//
//    ListActionBar(
//        items = listOf(
//            ActionChip("Window", onClick = {
//                navController.navigate(
//                    "buildings/${buildingId}/floors/${floorId}/windows/${roomId}/windows/new/form"
//                )
//            }),
////                ActionChip("Sample Window", onClick = {})
//        ),
//        onBack = { navController.navigate("buildings/${buildingId}/floors/${floorId}") }
//    )
//
//    if (windows.isNotEmpty()) {
//
//        fun getWindowLabel(item: Window, name: String): String {
//            return item.name
//        }
//
//        Column() {
//
//            com.artbird.onsite.ui.components.List<Window>(
//                windows,
//                selectedWindowIndex,
//                fields = listOf("name"),
//                onGetLabel = ::getWindowLabel,
//                onSelect = ::handleSelectWindow,
//                menus = menus
//            )
//        }
////            WindowList(windows, ::handleAdd, ::handleEdit, ::handleDelete, ::handleView)
//    }
//}
@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewWindowList(){
    val windows = listOf(
        Window("", "Window 1", "",
            WindowWidth(),
            WindowHeight(),
            type ="Normal",
            "L",
            "L",
            1,
            mountPosition = "Inside",
            BafflePosition(),
            "",
            "",
            frameStyle = "L Frame",
            originalFrameStyle = "Normal",
            lockers = listOf<Locker>(),
//            room = BaseEntity("", ""),
//            appointment = BaseAppointment("", "title")
        ),
        Window("", "Window 2", "",
            WindowWidth(),
            WindowHeight(),
            "Normal",
            "L",
            "L",
            1,
            mountPosition = "Inside",
            BafflePosition(),
            "",
            "",
            frameStyle = "L Frame",
            originalFrameStyle = "Normal",
            lockers = listOf<Locker>(),
//            room = BaseEntity("", ""),
//            appointment = BaseAppointment("", "title")
        ),
    )
    SLTheme {
        WindowList(
            windows,
            selectedIndex = 1
        )
    }
}
