package com.artbird.onsite.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artbird.onsite.ui.theme.SLTheme

data class RadioOption(
    var id: String ="",
    var value: String = "",
    var label: String = ""
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RadioGroup(
    options: List<RadioOption> = listOf(),
    onSelect: (it: RadioOption) -> Unit = {},

){
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(
        if(options.isEmpty()) RadioOption() else options[0])
    }

    // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    Column(Modifier.selectableGroup()) {
        options.forEach { it ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(36.dp)
                    .selectable(
                        selected = (selectedOption.value == it.value),
                        onClick = { onSelect(it) },
                        role = Role.RadioButton
                    )
                    .padding(
                      start = 10.dp
                    ),
//                    .background(
//                        color = colorScheme.surface
//                    ),

                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected =  (selectedOption.value == it.value),
                    onClick = null // null recommended for accessibility with screenreaders
                )
                Text(
                    text = it.label,
                    color = colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

//@Preview(
//    name="Light Mode",
//    uiMode = Configuration.UI_MODE_NIGHT_YES
//)
@Preview(showBackground = true)
@Composable
fun PreviewRadioGroup(){
    val options = listOf<RadioOption>(
            RadioOption("in coming", value = "in comming", label = "Incoming"),
            RadioOption("out going", value = "out going", label = "Outgoing"),
            RadioOption("missed", value = "missed", label = "Missed"),
    )

//    SLTheme {
        RadioGroup(
            options
        )
//    }
}