package com.artbird.onsite

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.artbird.onsite.ui.theme.SLTheme

data class MenuItem(val label: String, val path : String, val icon: ImageVector)

@Composable
fun MyBottomAppBar(
    roleName: String,
    onClick: (item: MenuItem) -> Unit
){

    val menuItems = when (roleName) {
        "partner" -> listOf(
            MenuItem("Client", "clients", Icons.Filled.Group),
            MenuItem("Settings", "settings", Icons.Filled.Settings),
        )
        else -> listOf()
    }

    var selectedItem by remember { mutableStateOf(0) }

    NavigationBar {
        menuItems.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(item.label) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    onClick(item)
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
fun PreviewBottomAppBar(){
    SLTheme {
        MyBottomAppBar(
            "partner",
            {}
        )
    }
}