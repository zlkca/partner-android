package com.artbird.onsite.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artbird.onsite.ui.theme.SLTheme

class TestListItem (
    var name: String,
    var type: String,
    var status: String,
)

@Composable
fun <T> ListItem(
    item: T,
    index: Int,
    selectedIndex: Int,
    fields: List<String>,
    onGetLabel: (it: T, fieldName: String) -> String,
    onClick: (index: Int) -> Unit,
    onSelectMenu: (index: Int) -> Unit = {},
    menus: List<DropdownMenuItem> = listOf(),
){
    val colorScheme = MaterialTheme.colorScheme

    Row(
        modifier = Modifier
            .background(
                color = if (selectedIndex == index) colorScheme.primary else colorScheme.background
            )
            .clickable { onClick(index) }
    ) {

        fields.forEach { name ->
            Box(modifier = Modifier
                .padding(8.dp)
                .weight(1f)) {
                Text(
                    text = onGetLabel(item, name),
                    color = if (selectedIndex == index) colorScheme.onPrimary else colorScheme.onBackground)
            }
        }

        DropdownMenuList(
            selectedIndex,
            onClick = { onSelectMenu(index) },
            menus,
        )
    }
}


@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewListItem(){
    var selectedIndex by remember { mutableStateOf(1) }

    fun handleSelect(index: Int){
        selectedIndex = index
    }

    fun handleHightlight(index: Int){
        selectedIndex = index
    }

    fun getLabel(item: TestListItem, name: String): String {
        return if(name == "name") item.name else if(name == "status") item.status else ""
    }

    val item = TestListItem("Area1", "Living Room", "Active")
    val menus: List<DropdownMenuItem> = listOf(
        DropdownMenuItem("edit", "Edit", Icons.Outlined.Edit, "Edit", {}),
        DropdownMenuItem("delete", "Delete", Icons.Outlined.Delete, "Delete", {}),
    )

    SLTheme {
        Column() {
            ListItem<TestListItem>(
                item,
                index = 0,
                selectedIndex,
                listOf("name", "type", "status"),
                onGetLabel = ::getLabel,
                ::handleSelect,
                ::handleHightlight,
                menus = menus,
            )
            ListItem<TestListItem>(
                item,
                index = 1,
                selectedIndex,
                listOf("name", "type", "status"),
                onGetLabel = ::getLabel,
                ::handleSelect,
                ::handleHightlight,
                menus = menus,
            )
            ListItem<TestListItem>(
                item,
                index = 2,
                selectedIndex,
                listOf("name", "type", "status"),
                onGetLabel = ::getLabel,
                ::handleSelect,
                ::handleHightlight,
                menus = menus,
            )
        }
    }
}