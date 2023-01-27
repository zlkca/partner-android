package com.artbird.onsite.ui.components

import androidx.compose.foundation.layout.Column
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T>SearchList(
    keyword: String,
    placeholder: String,
    items: List<T>,
    onSelect: (index: Int) -> Unit,
    onSearch: (keyword: String) -> Unit = {},
    onBack: () -> Unit = {},
    onClear: () -> Unit = {},
    itemContent: @Composable (T, selected: Boolean, index: Int) -> Unit = {it, selected, index -> }
){
    var selectedIndex by remember { mutableStateOf(-1) }


    Column(){

         TextField(
             value = keyword,
             onValueChange = {
//                 if(keyword.isNotEmpty()
                     onSearch(it)
//                 }
             },
                 modifier = Modifier.fillMaxWidth(),
                 placeholder = { Text(text = placeholder) },
                 leadingIcon = {Icon(Icons.Filled.Search, contentDescription = "Search")},
                 trailingIcon = {
                     if(keyword.isEmpty()){
                         Icon(
                             Icons.Filled.ArrowBack,
                             contentDescription = "Back",
                             modifier = Modifier.clickable {
                                 onBack()
                             }
                         )
                     }else{
                         Icon(Icons.Filled.Clear,
                             contentDescription = "Clear",
                             modifier = Modifier.clickable {
                                 onClear()
                             })
                     }

                 }
         )

         com.artbird.onsite.ui.components.List<T>(
             items,
             selectedIndex,
             onSelect = onSelect,
             itemContent = itemContent
         )
    }
}


@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewSearchList() {
    val items = listOf(
        TestListItem("Gary", "High", "Done", ) ,
        TestListItem("Linda", "Bay", "In Progress", ),
        TestListItem("Samantha", "Normal", "Not Start", ) ,
        TestListItem("Sarah", "Abnormal", "Not Start", ),
        TestListItem("Eugene", "High", "Done", ) ,
        TestListItem("Maya", "Bay", "In Progress", ),
        TestListItem("Gregory", "Normal", "Not Start", ) ,
        TestListItem("Kirstin", "High", "Done", ) ,
        TestListItem("Andrea", "Bay", "In Progress", ),
        TestListItem("Gloria", "Normal", "Not Start", ) ,
        TestListItem("Maggie", "Abnormal", "Not Start", ),
        TestListItem("Lori", "High", "Done", ) ,
        TestListItem("Hershel", "Bay", "In Progress", ),
        TestListItem("Allen", "Normal", "Not Start", ) ,
    )

    SearchList<TestListItem>(
        "a",
        "Find Client",
        items,
//        selectedIndex = selectedIndex,
        onSelect = {index -> },
        onSearch = {keyword -> },
        itemContent = {it, selected, index -> MyListItem(it, selected, index)}
    )
}