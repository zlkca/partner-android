package com.artbird.onsite.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ActionButton(icon: ImageVector,
                 description: String,
                 onClick: () -> Unit,
                 modifier: Modifier = Modifier.padding(end = 8.dp),
){
    IconButton(onClick = onClick, modifier=modifier) {
        Icon(icon, contentDescription = description)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewActionButton(){
    fun handleClick(){

    }
        Row() {
            ActionButton(Icons.Outlined.Visibility, "View", ::handleClick)
            ActionButton(Icons.Outlined.Edit, "Edit", ::handleClick)
            ActionButton(Icons.Outlined.Add, "Add", ::handleClick)
            ActionButton(Icons.Outlined.Delete, "Delete", ::handleClick)
        }

}

