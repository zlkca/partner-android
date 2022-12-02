package com.artbird.onsite.ui.window

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun WindowListScreen(navController: NavController,
                     roomId: String?,
                     windowViewModel: WindowViewModel
) {
    val windows by windowViewModel.windows.observeAsState(arrayListOf())

    LaunchedEffect(key1 = roomId){
        if(roomId != null) {
            windowViewModel.getWindowsByRoomId(roomId)
        }
    }

    fun handleView(id: String) {
        navController.navigate("windows/${id}")
    }

    fun handleAdd(id: String) {
        navController.navigate("windows/${id}")
    }

    fun handleEdit(id: String) {
        navController.navigate("windows/${id}")
    }

    fun handleDelete(id: String) {
        navController.navigate("windows/${id}")
    }

    Column() {


//        if (windows.isNotEmpty()) {
//            WindowList(windows, ::handleAdd, ::handleEdit, ::handleDelete, ::handleView)
//        }

        Button(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .focusTarget(),
            onClick = { navController.popBackStack() }
        ) {
            Text(text = "Back")
        }

    }
}