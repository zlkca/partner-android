package com.artbird.onsite.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> AccordionListItem(
    item: T,
    index: Int,
    selectedIndex: Int,
    expanded: Boolean,
    onClick: (i: Int) -> Unit,
    onGetTitle: (it: T) -> String,
    content: @Composable () -> Unit
) {

    val rotateState = animateFloatAsState(
        targetValue = if (expanded) 180F else 0F,
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Card(

            shape = RoundedCornerShape(0.dp),
            onClick = { onClick(index) }) {
            Box(
                modifier = Modifier.background(color = if (selectedIndex == index) Color.DarkGray else Color.White),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = onGetTitle(item),
                        modifier = Modifier.fillMaxWidth(0.30F),
                        color = if (selectedIndex == index) Color.White else Color.DarkGray
//                    style = MaterialTheme.typography.subtitle1
                    )
                    Icon(
                        Icons.Default.ArrowDropDown, "",
                        modifier = Modifier
                            .rotate(rotateState.value)
//                            .background(color = if (selectedIndex == index) Color.White ),
                    )
                }
            }
        }
        Divider()
        AnimatedVisibility(
            visible = expanded,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(16.dp)
            ) {
                // Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                content()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAccordionListItem() {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }

    fun handleSelect( index: Int ){
        selectedIndex = index
        expanded = !expanded // index != 0
    }

    fun getLabel(item: String): String {
        return item
    }

    AccordionListItem(
        "Living Room",
        0,
        selectedIndex,
        expanded,
        onClick = ::handleSelect,
        onGetTitle = ::getLabel,
        content = { Text("Hello") }
    )
}