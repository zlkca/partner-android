@file:OptIn(ExperimentalMaterial3Api::class)

package com.artbird.onsite.ui.components


import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
//import androidx.compose.material.icons.outlined.Add
//import androidx.compose.material.icons.outlined.Delete
//import androidx.compose.material.icons.outlined.Edit
//import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.ui.unit.dp

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.CalendarToday
//import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material3.*
import androidx.compose.material3.Card

import androidx.compose.runtime.*
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview

//import com.shutterlux.onsite.ui.components.ActionButton;



@Composable
fun <T> AccordionList(
    items: List<T>,
    expandedList: MutableList<Boolean>,
    itemContent: @Composable (item: T, index: Int, selectedIndex: Int, expanded: Boolean) -> Unit,
    selectedIndex: Int,
    onClick: (i: Int) -> Unit,
    onGetTitle: (it: T) -> String,
) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        items.forEachIndexed { index, it ->
            Column(modifier = Modifier.padding(0.dp)
            ) {
                AccordionListItem<T>(
                    it,
                    index,
                    selectedIndex,
                    expandedList[index],
                    onClick,
                    onGetTitle,
                    content = {
                        itemContent(it, index, 0, expandedList[index])
                    }
                )
            }
        }
    }
}



class TestAccordionItem(
    var name: String,
    var expanded: Boolean
)

@Preview(showBackground = true)
@Composable
fun PreviewAccordionList(){
    var selectedIndex by remember { mutableStateOf(0) }
    val nItems = 2
    var expandedList by remember {
        mutableStateOf(MutableList(nItems){false})
    }

    fun handleSelect(idx: Int){

        var myList = mutableListOf<Boolean>()

        expandedList.forEachIndexed { index, b ->
            Log.i("zlk", "index:${index.toString()} - selected:${idx.toString()}:  ${b.toString()}")
            if(index == idx) {
                myList.add(index, !b)
            }else{
                myList.add(index, b)
            }
        }
        expandedList = myList
        selectedIndex = idx
    }

    fun getLabel(item: TestAccordionItem): String {
        return item.name
    }

    // init val
    var items = listOf(
        TestAccordionItem("Living Room", true) ,
        TestAccordionItem("Living Room2", false) ,
    )


    AccordionList<TestAccordionItem>(
        items,
        expandedList = expandedList,
        selectedIndex = selectedIndex,
        onClick = ::handleSelect,
        onGetTitle = ::getLabel,
        itemContent = { it, index, selectedIndex, expanded ->
            Text("Hi")
        },
    )
}