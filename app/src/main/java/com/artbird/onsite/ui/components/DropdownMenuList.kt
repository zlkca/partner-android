package com.artbird.onsite.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class DropdownMenuItem (
    val name: String,
    val label: String,
    var icon: ImageVector,
    var iconDescription: String = "",
    val onClick: (index: Int) -> Unit = {},
)

@Composable
fun DropdownMenuList(
    selectedIndex: Int,
    onClick: () -> Unit = {},
    menus: List<DropdownMenuItem> = listOf()
) {
    var expanded by remember { mutableStateOf(false) }

    if(menus != null && menus.isNotEmpty()){
        Box(
        ) {
            IconButton(onClick = {
                onClick()
                expanded = true
            }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Localized description")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                Modifier.offset(8.dp, 8.dp)
            ) {

                menus.forEachIndexed { index, it ->
                    DropdownMenuItem(
                        text = { Text(it.label) },
                        onClick = {
                            it.onClick(selectedIndex)
                            expanded = false
                        },
                        leadingIcon = {
                            Icon(
                                it.icon, // Icons.Outlined.Edit,
                                contentDescription = null
                            )
                        })
                }
            }
        }
    }
}
