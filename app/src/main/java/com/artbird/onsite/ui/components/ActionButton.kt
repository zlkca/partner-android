package com.artbird.onsite.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artbird.onsite.ui.theme.SLTheme

@Composable
fun ActionButton(icon: ImageVector,
                 description: String,
                 onClick: () -> Unit,
                 modifier: Modifier = Modifier.padding(end = 8.dp),
){
//    val colorScheme = MaterialTheme.colorScheme
    IconButton(onClick = onClick, modifier=modifier) {
        Icon(icon, contentDescription = description)
    }
}

@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewActionButton(){
    fun handleClick(){

    }
    SLTheme {
        Row() {
            ActionButton(Icons.Outlined.Visibility, "View", ::handleClick)
            ActionButton(Icons.Outlined.Edit, "Edit", ::handleClick)
            ActionButton(Icons.Outlined.Add, "Add", ::handleClick)
            ActionButton(Icons.Outlined.Delete, "Delete", ::handleClick)
        }
    }

}

