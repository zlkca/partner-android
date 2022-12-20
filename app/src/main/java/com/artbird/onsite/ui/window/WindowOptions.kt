package com.artbird.onsite.ui.window

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
    windowType: String = "",
    windowFrameStyles: WindowFrameStyles = WindowFrameStyles(),
    dividerRail:WindowDividerRail = WindowDividerRail(),
    onChange: (name: String, v: String) -> Unit = { name, v -> },
    onFrameStyleChange: (style: WindowFrameStyles) -> Unit = {},
){

    var typeOptions by remember { mutableStateOf(listOf<OptionItem>(
        OptionItem("Normal", "Normal"),
        OptionItem("Bay", "Bay"),
        OptionItem("Sliding", "Sliding"),
        OptionItem("Arch", "Arch"),
        OptionItem("Bi-Fold", "Bi-Fold"),
        OptionItem("High", "High"),
    )) }


    var directionOptions by remember { mutableStateOf(listOf<OptionItem>()) }

    var openingDirection by remember { mutableStateOf("") }

    val verticalScrollState = rememberScrollState()

    fun toOptions(list: List<String>): List<OptionItem> {
        return list.map { OptionItem(it, it) }
    }

    fun getDirectionOptions(nWindows: String, windowType: String): List<OptionItem> {
        return when(nWindows){
            "1" -> toOptions(listOf("L", "R"))
            "2" -> toOptions(listOf("LR", "LL", "RR"))
            "3" -> {
                when(windowType){
                    "Bay" -> toOptions(listOf("LTLTR", "LTRTR"))
                    else -> toOptions(listOf("LRTR", "LTLR", "LLL", "RRR"))
                }
            }
            "4" -> {
                when(windowType){
                    "Bay" -> toOptions(listOf("LTLRTR"))
                    else -> toOptions(listOf("LRTLR", "LLRR"))
                }
            }
            "5" -> {
                when(windowType){
                    "Bay" -> toOptions(listOf("LTLRTRTR"))
                    else -> toOptions(listOf("LRTLRTR", "LRTLTLR", "LTLRTLR"))
                }
            }
            "6" -> toOptions(listOf<String>("LRTLRTLR"))
            "7" -> toOptions(listOf<String>("LRTLRTLRTR"))
            "8" -> toOptions(listOf<String>("LRTLRTLRTLR"))
            else -> listOf()
        }
    }

    Column(modifier = Modifier
        .verticalScroll(verticalScrollState)
        .width(310.dp)
        .height(560.dp)
    ) {
        NumberInput(
            value = numOfWindows,
            onValueChange = {
                directionOptions = getDirectionOptions(it, windowType)
                onChange("numOfWindows", it)
            },
            label = "Num of Windows",
        )

        Select(
            value = windowType,
            onValueChange = {
                directionOptions = getDirectionOptions(numOfWindows, it.value)
                onChange("windowType", it.value)
            },
            label = "Window Type",
            options = typeOptions
        )

        if(numOfWindows.isNotEmpty() && windowType.isNotEmpty()) {
            Select(
                value = openingDirection,
                onValueChange = {
                    onChange("openingDirection", it.value)
                },
                label = "Open Direction",
                options = directionOptions
            )
        }

        FrameStyleSelect(
            windowFrameStyles,
            onChange = onFrameStyleChange
        )

        Column(modifier = Modifier.padding(start=10.dp),) {
            ImperialLengthInput(
                "Divider Rail Heigactoht",
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
            windowType = "Normal",
            dividerRail = WindowDividerRail(height="20in5", top="42in7")
        )
    }
}
