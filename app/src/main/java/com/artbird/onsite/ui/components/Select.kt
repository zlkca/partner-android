package com.artbird.onsite.ui.components

//import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
//import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.toSize

//import androidx.compose.ui.unit.toSize



data class OptionItem (
    val label: String,
    val onClick: (OptionItem)->Unit
)


@Composable
fun Select(
    label: String,
    value: String,
    options: List<OptionItem>,
    onValueChange: (v: String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth().padding(8.dp),
    ) {
    var expanded by remember { mutableStateOf(false) }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (expanded)
        Icons.Filled.ArrowDropUp //it requires androidx.compose.material:material-icons-extended
    else
        Icons.Filled.ArrowDropDown


    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = { Text(label) },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            options.forEach {
                DropdownMenuItem(
                    text= { Text(it.label) },
                    onClick = {
                        expanded = false
                        it.onClick(it)
                    } )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSelect(){
    var selectedText by remember { mutableStateOf("") }
    fun handleClick(it: OptionItem){
        selectedText = it.label
    }
    val options = listOf(
        OptionItem("Item1",::handleClick),
        OptionItem("Item2",::handleClick),
        OptionItem("Item3",::handleClick)
    )


    Select(
        label = "Type",
        value = selectedText,
        options = options,
        onValueChange = { selectedText = it }
    )
}