package com.artbird.onsite.ui.window

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artbird.onsite.ui.components.ImperialLengthInput
import com.artbird.onsite.ui.theme.SLTheme
import com.artbird.onsite.ui.utils.toImparialLength
import com.artbird.onsite.ui.utils.toImparialLengthString

@Composable
fun WindowHeightInput(
    width: Int,
    unit: String = "inch",
    left: String = "", // eg. "12in13" or ""
    middle: String = "",
    right: String = "",
    onChange: (name:String, s:String) -> Unit = {name,s ->}
){
    Row(
        modifier = Modifier.width(width.dp)
    ) {
        Box(modifier = Modifier
            .weight(1.0F)
        ) {
            ImperialLengthInput("Left", toImparialLength(left),
                onChange = {
                    onChange("left", toImparialLengthString(it))
                })
        }
        Box( contentAlignment =  Alignment.Center,
            modifier = Modifier
                .weight(1.0F)
        ) {
            ImperialLengthInput("Middle", toImparialLength(middle),
                onChange = {
                    onChange("middle", toImparialLengthString(it))
                })
        }
        Box(modifier = Modifier
            .weight(1.0F),
            contentAlignment =  Alignment.CenterEnd
        ) {
            ImperialLengthInput("Right", toImparialLength(right),
                onChange = {
                    onChange("right", toImparialLengthString(it))
                })
        }
    }
}

@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.AUTOMOTIVE_1024p,
    widthDp = 2560, heightDp = 1600
)
@Preview(showBackground = true)
@Composable
fun PreviewWindowHeightInput(){
    SLTheme {
        WindowHeightInput(
            width = 800,
            left = "12in13",
            middle = "in15"
        )
    }
}