package com.artbird.onsite.ui.components

//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.Button
//import androidx.compose.material3.Divider
//
import androidx.compose.material3.Text
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.CalendarToday
//import androidx.compose.material.icons.filled.Dashboard
//import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artbird.onsite.ui.theme.SLTheme


@Composable
fun <T> List(
    items: List<T>,
    selectedIndex: Int,
    fields: List<String> = listOf(),
    onGetLabel: (it: T, fieldName: String) -> String = {it, fn -> "Hi"},
    onSelect: (index: Int) -> Unit,
    onSelectMenu: (index: Int) -> Unit = {},
    menus: List<DropdownMenuItem> = listOf(),
    itemContent: @Composable (T, selected: Boolean, index: Int) -> Unit = {it, selected, index -> },
) {
    val colorScheme = MaterialTheme.colorScheme
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        items.forEachIndexed{ index, it ->
//                    ListItem<T>(
//                        it,
//                        index,
//                        selectedIndex,
//                        fields,
//                        onGetLabel,
//                        onSelect,
//                        onSelectMenu,
//                        menus,
//                    )




            Row(
                modifier = Modifier
                    .background(
                        color = if (selectedIndex == index) colorScheme.primary else colorScheme.background
                    )
                    .clickable { onSelect(index) }
            ) {
                Box(modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)) {

                        itemContent(it, selectedIndex == index, index)
                    }

//                DropdownMenuList(
//                    selectedIndex,
//                    onClick = { onSelectMenu(index) },
//                    menus,
//                )
            }

            }
    }
}

@Composable
fun MyListItem(item: TestListItem, selected: Boolean, index:Int){
    val colorScheme = MaterialTheme.colorScheme
    Column(){
        Text(
            text = item.name,
            color = if (selected) colorScheme.onPrimary else colorScheme.onBackground
        )
        Text(
            text = item.status,
            color = if (selected) colorScheme.onPrimary else colorScheme.onBackground
        )
    }
}

@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewList(){
    var selectedIndex by remember { mutableStateOf(0) }
//    var dropdownOpened by remember { mutableStateOf(false) }

    fun handleSelect(index: Int){
        selectedIndex = index
    }

    fun handleHighlight(index: Int){
        selectedIndex = index
    }

    fun getLabel(item: TestListItem, name: String): String {
        return if(name == "name") item.name else if(name == "status") item.status else ""
    }
    val menus: List<DropdownMenuItem> = listOf(
        DropdownMenuItem("edit", "Edit", Icons.Outlined.Edit, "Edit", {}),
        DropdownMenuItem("delete", "Delete", Icons.Outlined.Delete, "Delete", {}),
    )
    val items = listOf(
        TestListItem("Window 1", "High", "Done", ) ,
        TestListItem("Window 2", "Bay", "In Progress", ),
        TestListItem("Window 3", "Normal", "Not Start", ) ,
        TestListItem("Window 4", "Abnormal", "Not Start", ),
        TestListItem("Window 1", "High", "Done", ) ,
        TestListItem("Window 2", "Bay", "In Progress", ),
        TestListItem("Window 3", "Normal", "Not Start", ) ,
        TestListItem("Window 4", "Abnormal", "Not Start", ),
        TestListItem("Window 1", "High", "Done", ) ,
        TestListItem("Window 2", "Bay", "In Progress", ),
        TestListItem("Window 3", "Normal", "Not Start", ) ,
        TestListItem("Window 4", "Abnormal", "Not Start", ),
        TestListItem("Window 1", "High", "Done", ) ,
        TestListItem("Window 2", "Bay", "In Progress", ),
        TestListItem("Window 3", "Normal", "Not Start", ) ,
        TestListItem("Window 4", "Abnormal", "Not Start", ),
        TestListItem("Window 1", "High", "Done", ) ,
        TestListItem("Window 2", "Bay", "In Progress", ),
        TestListItem("Window 3", "Normal", "Not Start", ) ,
        TestListItem("Window 4", "Abnormal", "Not Start", ),
        TestListItem("Window 1", "High", "Done", ) ,
        TestListItem("Window 2", "Bay", "In Progress", ),
        TestListItem("Window 3", "Normal", "Not Start", ) ,
        TestListItem("Window 4", "Abnormal", "Not Start", ),
        TestListItem("Window 1", "High", "Done", ) ,
        TestListItem("Window 2", "Bay", "In Progress", ),
        TestListItem("Window 3", "Normal", "Not Start", ) ,
        TestListItem("Window 4", "Abnormal", "Not Start", ),
        TestListItem("Window 1", "High", "Done", ) ,
        TestListItem("Window 2", "Bay", "In Progress", ),
        TestListItem("Window 3", "Normal", "Not Start", ) ,
        TestListItem("Window 4", "Abnormal", "Not Start", ),
        TestListItem("Window 1", "High", "Done", ) ,
        TestListItem("Window 2", "Bay", "In Progress", ),
        TestListItem("Window 3", "Normal", "Not Start", ) ,
        TestListItem("Window 4", "Abnormal", "Not Start", ),
    )

    SLTheme {
        Column() {
            Column() {
                Text("Alfred Sisley")
                Text("3 minutes ago")
                Text("Alfred Sisley")
                Text("3 minutes ago")
                Text("Alfred Sisley")
                Text("3 minutes ago")
            }
            List<TestListItem>(
                items,
                selectedIndex = selectedIndex,
                fields = listOf("name", "status"),
                onGetLabel = ::getLabel,
                ::handleSelect,
                ::handleHighlight,
                menus = menus,
                itemContent = {it, selected, index -> MyListItem(it, selected, index)}
            )
        }
    }

//    if(dropdownOpened){
//        DropdownMenuList()
//    }
}


// Lazy list
//Column(modifier = Modifier.padding(16.dp)) {
//    LazyColumn(modifier = Modifier.fillMaxHeight()) {
//        if (!appointments.isNullOrEmpty()) {
//            items(appointments) { it ->
//                Column {
//                    Text(it.title)
//                    Text(it.client.username)
//                    Text(it.employee.username)
//                    Text(it.startTime)
//                    Text(it.endTime)
//                    Button(onClick = { navController.navigate("appointments/${it._id}") }) {
//                        Text(text = "View Details")
//                    }
//                    Divider(color = Color.Black)
//                }
//            }
//        }
//    }
//
//}