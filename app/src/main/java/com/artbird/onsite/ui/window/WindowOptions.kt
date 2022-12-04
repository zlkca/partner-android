package com.artbird.onsite.ui.window

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artbird.onsite.ui.components.NumberInput

@Composable
fun WindowOptions(
    numOfWindows: Int = 1,
    onChange: (name: String, v: String) -> Unit = { name, v -> }
){
    Column(modifier = Modifier.width(500.dp)) {
        NumberInput(
            value = numOfWindows.toString(),
            onValueChange = { onChange("numOfWindows", it) },
            label = "Num of Windows",
        )
    }
}