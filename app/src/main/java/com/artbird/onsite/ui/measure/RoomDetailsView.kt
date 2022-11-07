package com.artbird.onsite.ui.appointment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.Building
import com.artbird.onsite.domain.Floor
import com.artbird.onsite.domain.Room
import com.artbird.onsite.domain.Window
import com.artbird.onsite.ui.building.RoomForm
import com.artbird.onsite.ui.components.ActionChip
import com.artbird.onsite.ui.components.OldFormActionBar
import com.artbird.onsite.ui.components.ListActionBar
import com.artbird.onsite.ui.window.WindowViewModel


@Composable
fun RoomDetailsView(
    // navController: NavController,
    building: Building,
    floor: Floor,
    roomIndex: Int,
    room: Room,
    windowViewModel: WindowViewModel,
    onNext: (step: String, index: Int) -> Unit,
    onPrevious: (step: String, index: Int) -> Unit
) {
    var mode by remember { mutableStateOf("view") }
    val windows by windowViewModel.windows.observeAsState(arrayListOf())

    LaunchedEffect(key1 = room._id){
        if(room._id != null) {
            windowViewModel.getWindowsByRoomId(room._id)
        }
    }

    fun handleView(id: String) {
//            navController.navigate("windows/${id}")
    }

    fun handleAdd(id: String) {
//            navController.navigate("windows/${id}")
    }

    fun handleDelete(id: String) {
//            navController.navigate("windows/${id}")
    }

    fun handleBack(){
        onPrevious("rooms", roomIndex)
    }

    fun handleSubmit(){
        mode = "view"
    }

    fun handleEdit(){
        mode = "edit"
    }

    fun handleCancel(){
        mode = "view"
    }



    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        OldFormActionBar(mode, onCancel = ::handleCancel, onBack = ::handleBack, onSave = ::handleSubmit, onEdit = ::handleEdit)

        if(mode == "edit") {
            RoomForm(room, ::handleSubmit)
        }else {
            Text(text = "${building.name}", modifier = Modifier.padding(8.dp))
            Text(text = "${floor.name}", modifier = Modifier.padding(8.dp))
            Text(text = "${room.name}", modifier = Modifier.padding(8.dp))
            Text(text = "${room.notes}", modifier = Modifier.padding(8.dp))

            ListActionBar(items = listOf(
                ActionChip("Window", onClick = {}),
                ActionChip("Sample Window", onClick = {})
            ))
            if (windows.isNotEmpty()) {

                var selectedIndex by remember { mutableStateOf(0) }

                fun selectWindow(index: Int) {
                    selectedIndex = index
                    onNext("window", selectedIndex)
                }

                fun getWindowLabel(item: Window, name: String): String {
                    return item.name
                }

                Column() {

                    com.artbird.onsite.ui.components.List<Window>(
                        windows,
                        selectedIndex,
                        fields = listOf("name"),
                        onGetLabel = ::getWindowLabel,
                        onSelect = ::selectWindow,
                    )
                }
//            WindowList(windows, ::handleAdd, ::handleEdit, ::handleDelete, ::handleView)
            }
        }

    }
}