package com.artbird.onsite.ui.window

import android.content.res.Configuration
import android.graphics.Path
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.components.ImperialLengthInput
import com.artbird.onsite.ui.components.NumberInput
import com.artbird.onsite.ui.components.OptionItem
import com.artbird.onsite.ui.components.Select
import com.artbird.onsite.ui.theme.SLTheme
import com.artbird.onsite.ui.utils.toImparialLength
import com.artbird.onsite.ui.utils.toImparialLengthString

@Composable
fun WindowOptions(
    numOfWindows: String = "",
    onChange: (name: String, v: String) -> Unit = { name, v -> }
){

    var typeOptions by remember { mutableStateOf(listOf<OptionItem>(
        OptionItem("Normal Window", "normal"),
        OptionItem("Bay Window", "bay"),
        OptionItem("Sliding Window", "sliding"),
        OptionItem("Arch Window", "arch"),
        OptionItem("Bi-Fold Window", "bi-fold"),
        OptionItem("High Window", "high"),
    )) }

    var frameOptions by remember { mutableStateOf(listOf<OptionItem>(
        OptionItem("Z", "Z"),
        OptionItem("big Z", "bigZ"),
        OptionItem("HG", "HG"),
        OptionItem("L", "L"),
    )) }

    var windowType by remember { mutableStateOf(OptionItem())}

    var directionOptions by remember { mutableStateOf(listOf<OptionItem>()) }

    var openingDirection by remember { mutableStateOf("") }
    var frameStyle by remember {
        mutableStateOf(OptionItem())
    }
    var dividerRail by remember {
        mutableStateOf(DividerRail(height = "", top = ""))
    }

    fun toOptions(list: List<String>): List<OptionItem> {
        return list.map { OptionItem(it, it) }
    }

    fun getDirectionOptions(nWindows: String, windowType: String): List<OptionItem> {
        return when(nWindows){
            "1" -> toOptions(listOf("L", "R"))
            "2" -> toOptions(listOf("LR", "LL", "RR"))
            "3" -> {
                when(windowType){
                    "bay" -> toOptions(listOf("LTLTR", "LTRTR"))
                    else -> toOptions(listOf("LRTR", "LTLR", "LLL", "RRR"))
                }
            }
            "4" -> {
                when(windowType){
                    "bay" -> toOptions(listOf("LTLRTR"))
                    else -> toOptions(listOf("LRTLR", "LLRR"))
                }
            }
            "5" -> {
                when(windowType){
                    "bay" -> toOptions(listOf("LTLRTRTR"))
                    else -> toOptions(listOf("LRTLRTR", "LRTLTLR", "LTLRTLR"))
                }
            }
            "6" -> toOptions(listOf<String>("LRTLRTLR"))
            "7" -> toOptions(listOf<String>("LRTLRTLRTR"))
            "8" -> toOptions(listOf<String>("LRTLRTLRTLR"))
            else -> listOf()
        }
    }

    Column(modifier = Modifier.width(300.dp)) {
        NumberInput(
            value = numOfWindows,
            onValueChange = {
//                directionOptions = getDirectionOptions(it, windowType)
                onChange("numOfWindows", it)
                            },
            label = "Num of Windows",
        )

        Select(
            value = windowType.label,
            onValueChange ={
                directionOptions = getDirectionOptions(it.value, windowType.value)
                windowType = it
                           },
            label = "Window Type",
            options = typeOptions
        )

        Select(
            value = openingDirection,
            onValueChange ={
                onChange("openingDirection", it.value)
            },
            label = "Open Direction",
            options = directionOptions
        )

        Select(
            value = frameStyle.label,
            onValueChange ={
                frameStyle = it
            },
            label = "Frame Style",
            options = frameOptions
        )

        Column(modifier = Modifier.padding(start=10.dp),) {
            ImperialLengthInput(
                "Divider Rail Height",
                toImparialLength(dividerRail.height),

                onChange = {
                    onChange("divider-height", toImparialLengthString(it))
                })

            ImperialLengthInput("Divider Rail From Top",
                toImparialLength(dividerRail.top),
                onChange = {
                    onChange("divider-top", toImparialLengthString(it))
                })
        }
    }
}

@Preview(
name="Light Mode",
uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewWindowOptions(){

    SLTheme {
        WindowOptions(
            numOfWindows = "2",
        )
    }
}
