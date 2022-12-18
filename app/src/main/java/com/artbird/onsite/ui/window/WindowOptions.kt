package com.artbird.onsite.ui.window

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artbird.onsite.ui.components.NumberInput

@Composable
fun WindowOptions(
    numOfWindows: String = "",
    windowType: String = "",
    onChange: (name: String, v: String) -> Unit = { name, v -> }
){
    //    val typeOptions = listOf(
//        OptionItem("Normal",::clickTypeOption),
//        OptionItem("Bay",::clickTypeOption),
//        OptionItem("Arch",::clickTypeOption),
//        OptionItem("Slide",::clickTypeOption),
//        OptionItem("Bi-Fold",::clickTypeOption),
//        OptionItem("High",::clickTypeOption),
//    )

    Column(modifier = Modifier.width(300.dp)) {
        NumberInput(
            value = numOfWindows,
            onValueChange = { onChange("numOfWindows", it) },
            label = "Num of Windows",
        )
    }
}