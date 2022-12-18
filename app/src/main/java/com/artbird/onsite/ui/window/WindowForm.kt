package com.artbird.onsite.ui.window

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.Window
import com.artbird.onsite.ui.components.*
import com.artbird.onsite.ui.theme.SLTheme


@Composable
fun WindowForm(
    window: Window,
    error: Map<String, String> = mapOf<String, String>(),
    onChange: (fieldName: String, value: String) -> Unit = {f,v -> },
    onSave: ()-> Unit = {},
    onCancel: ()-> Unit = {}
){
    var nWindows: String by remember { mutableStateOf("") }
    var directionOptions = listOf<String>()
    var windowType by remember { mutableStateOf("")}




    Row(
        horizontalArrangement = Arrangement.End
    ) {
        Column(modifier = Modifier.weight(1F)) {
            WindowCanvas(
                numOfWindows = if(nWindows.toDoubleOrNull() != null) nWindows.toInt() else 0,
                w = window.width,
                h = window.height,
                onChange = onChange
            )
        }

        WindowOptions(
            numOfWindows = nWindows,
            onChange = {name, v ->
                when(name){
                    "numOfWindows" -> {
                        nWindows = v
                        onChange("numOfWindows", v)
                    }
                }
            }
        )
    }

//    val verticalScrollState = rememberScrollState()
//    var width by remember { mutableStateOf(UIWindowWidth()) }
//    var height by remember { mutableStateOf(UIWindowHeight()) }
//
//    Column(
//        modifier = Modifier
//            .padding(8.dp)
//    ) {
//        FormActionBar(
//            onCancel,
//            onSave
//        )
//
//        Column(Modifier.verticalScroll(verticalScrollState)) {
//
//            Input(
//                value = window.name,
//                onValueChange = { onChange("name", it) },
//                label = "Name",
//            )
//
//            Input(
//                value = window.notes,
//                onValueChange = { onChange("notes", it) },
//                label = "Notes",
//            )
//
//                Text(text = "Width",
//                    modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 0.dp)
//                )
//                ImperialLengthInput(
//                    label = "Top",
//                    len = width.top,
//                    onChange = {
//                        width = UIWindowWidth(it, width.mid, width.bottom)
//                    }
//                )
//                ImperialLengthInput("Middle", width.mid, {it ->
//                    width = UIWindowWidth(width.top, it, width.bottom)
//                })
//                ImperialLengthInput("Bottom", width.bottom, {it ->
//                    width = UIWindowWidth(width.top, width.mid, it)
//                })
//
//
//                Text(text = "Height",
//                    modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 0.dp))
//                ImperialLengthInput("Left", height.left, { it ->
//                    height = UIWindowHeight(it, height.mid, height.right)
//                })
//                ImperialLengthInput("Middle", height.mid, {it ->
//                    height = UIWindowHeight(height.left, it, height.right)
//                })
//                ImperialLengthInput("Right", height.right, {it ->
//                    height = UIWindowHeight(height.left, height.mid, it)
//                })
//
//                NumberInput(
//                    value = numOfWindows,
//                    onValueChange = { numOfWindows = it },
//                    label = "Num of Windows",
//                )
//
//                Row(
//                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
//                ) {
//
//                    Input(
//                        value = position,
//                        onValueChange = { position = it },
//                        label = "Position",
//                        modifier = Modifier.weight(1f).padding(end=8.dp)
//                    )
//
//                    Input(
//                        value = openingDirection,
//                        onValueChange = { openingDirection = it },
//                        label = "Opening Direction",
//                        modifier = Modifier.weight(1f).padding(start=8.dp)
//                    )
//                }
//
//                Row(
//                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 10.dp)
//                ) {
//                    Select(
//                        value = type,
//                        onValueChange = { type = it },
//                        label = "Type",
//                        options = typeOptions,
//                        modifier = Modifier.weight(1f).padding(end=8.dp)
//                    )
//
//                    Select(
//                        value = mountPosition,
//                        onValueChange = { mountPosition = it },
//                        label = "Install Position",
//                        options = mountPositionOptions,
//                        modifier = Modifier.weight(1f).padding(start=8.dp)
//                    )
//                }
//
//                Row(
//                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 10.dp)
//                ) {
//                    Select(
//                        value = bladeSize,
//                        onValueChange = { bladeSize = it },
//                        label = "Blade Size",
//                        options = bladeOptions,
//                        modifier = Modifier.weight(1f).padding(end=8.dp)
//                    )
//
//                    Select(
//                        value = leverType,
//                        onValueChange = { leverType = it },
//                        label = "Lever Type",
//                        options = leverOptions,
//                        modifier = Modifier.weight(1f).padding(start=8.dp)
//                    )
//                }
//
//                Row(
//                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 10.dp)
//                ) {
//                    Select(
//                        value = originalFrameStyle,
//                        onValueChange = { originalFrameStyle = it },
//                        label = "Original Frame Style",
//                        options = originalFrameStyleOptions,
//                        modifier = Modifier.weight(1f).padding(end=8.dp)
//                    )
//
//                    Select(
//                        value = frameStyle,
//                        onValueChange = { frameStyle = it },
//                        label = "Frame Style",
//                        options = frameStyleOptions,
//                        modifier = Modifier.weight(1f).padding(start=8.dp)
//                    )
//                }
//
//
//                Text(text = "Baffle Position",
//                    modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 0.dp))
//                ImperialLengthInput("Top", bafflePosition.top, { it ->
//                    bafflePosition = UIBafflePosition(it, bafflePosition.bottom)
//                })
//                ImperialLengthInput("Bottom", bafflePosition.bottom, {it ->
//                    bafflePosition = UIBafflePosition(bafflePosition.top, it)
//                })
//
//
//                lockers.forEachIndexed { index, it, ->
//                    Text(text = "Locker ${index+1}",
//                        modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp))
//
//                    LockerForm(
//                        locker = it,
//                        onPositionChange = { name, pos ->
//                            updateLockerPosition(it.id, name, pos)
//                        },
//                        onSizeChange = { name, len ->
//                            updateLockerSize(it.id, name, len)
//                        },
//                    )
//                } // end of lockers
//
//                Box(modifier = Modifier.fillMaxWidth().height(60.dp).padding(bottom=30.dp))
//            }

}

@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    device = Devices.AUTOMOTIVE_1024p,
    widthDp = 2048, heightDp = 1536
)
@Preview(showBackground = true)
@Composable
fun PreviewWindowForm(){
    val window = Window("", "First Window", "",
//        windows = listOf(
//            Window("", "Living Window", ""),
//            Window("", "Family Window", ""),
//            Window("", "Dinning Window", ""),
//            Window("", "Kitchen", ""),
//        )
    )

//    val building = Building()
//    var address = Address("2", "", "235", "Front St", "Toronto", "ON", "L3R 0C7")

    SLTheme {
        WindowForm(
            window
        )
    }
}