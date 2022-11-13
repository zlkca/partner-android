package com.artbird.onsite.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



@Composable
fun OldFormActionBar(
    mode: String = "view",
    onCancel: () -> Unit = {},
    onBack: () -> Unit = {},
    onSave: () -> Unit = {},
    onEdit: () -> Unit = {},
){
    Column(){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Start
            ){
                if(mode=="edit") {
                    ActionButton(Icons.Outlined.Close, "Cancel", onCancel)
                }else{
                    ActionButton(Icons.Outlined.ArrowBack, "Back", onBack)
                }
            }

            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                horizontalArrangement = Arrangement.End
            ){
                if(mode=="edit") {
                    SmallButton("Save", onClick = onSave)
                }else{
                    ActionButton(Icons.Outlined.Edit, "Edit", onEdit)
                }
            }

        }
    }
}

@Composable
fun FormActionBar(
    onCancel: () -> Unit = {},
    onSave: () -> Unit = {},
){
    Column(){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Start
            ){
                ActionButton(Icons.Outlined.Close, "Cancel", onCancel)
            }

            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                horizontalArrangement = Arrangement.End
            ){
                SmallButton("Save", onClick = onSave)
            }
        }
    }
}

@Composable
fun SearchActionBar(
    onCancel: () -> Unit = {},
//    onOk: () -> Unit = {},
){
    Column(
        modifier = Modifier.fillMaxWidth(),
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Start
            ){
                ActionButton(Icons.Outlined.Close, "Cancel", onCancel)
            }

//            Row(
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(end = 8.dp),
//                horizontalArrangement = Arrangement.End
//            ){
//                SmallButton("Done", onClick = onOk)
//            }
        }
    }
}

@Composable
fun DetailsViewActionBar(
    onBack: () -> Unit = {},
    onEdit: () -> Unit = {},
    readOnly: Boolean = false,
){
    Column(
        modifier = Modifier.fillMaxWidth(),
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Start
            ){
                ActionButton(Icons.Outlined.ArrowBack, "Back", onBack)
            }

            if(!readOnly) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    ActionButton(Icons.Outlined.Edit, "Edit", onEdit)
                }
            }
        }
    }
}

data class SubmitButton(
    var label: String,
    val onClick: () -> Unit = {}
)

@Composable
fun PrintActionBar(
    onBack: () -> Unit = {},
    actionButton: SubmitButton,
){
    Column(){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Start
            ){
                    ActionButton(Icons.Outlined.ArrowBack, "Back", onBack)
            }

            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                horizontalArrangement = Arrangement.End
            ){
                SmallButton(actionButton.label, onClick = actionButton.onClick)
//                Button(
//                    onClick = {
////                            navController.navigate("quotes/${id}/pdf")
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth(0.5f)
//                        .height(60.dp)
//                        .padding(10.dp),
//                    shape = RoundedCornerShape(0.dp),
//                ) {
//                    Text(
//                        text = "Generate Pdf",
//                        fontSize = 13.sp
//                    )
//                }
            }

        }
    }
}

data class ActionChip(
    var label: String,
    var icon: ImageVector = Icons.Filled.Add,
    val onClick: () -> Unit = {}
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListActionBar(
    items: List<ActionChip>,
    onBack: () -> Unit = {},
){
    Column() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Start
            ) {
//                if(onBack != {}) {
//                    ActionButton(Icons.Outlined.ArrowBack, "Back", onBack)
//                }
            }

            Row(
                modifier = Modifier.weight(3f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                items.forEachIndexed { index, it ->
                    AssistChip(
                        onClick = it.onClick,
                        label = { Text(it.label) },
                        leadingIcon = {
                            Icon(
                                it.icon,
                                contentDescription = it.label,
                                Modifier.size(AssistChipDefaults.IconSize)
                            )
                        },
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFormActionBar(){
    val chips = listOf(
        ActionChip("Room", onClick = {}),
        ActionChip("Sample Room", onClick = {})
    )

    Column() {
        OldFormActionBar("view")
        OldFormActionBar("edit")
        PrintActionBar({}, SubmitButton("Save as Pdf", {}))
        ListActionBar(chips, { val a = 1 })
    }
}