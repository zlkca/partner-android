package com.artbird.onsite.ui.window

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.ImparialLength
import com.artbird.onsite.ui.components.ImperialLengthInput
import com.artbird.onsite.ui.theme.SLTheme
import com.artbird.onsite.ui.utils.toImparialLength
import com.artbird.onsite.ui.utils.toImparialLengthString

@Composable
fun WindowWidthInput(
    width: Float,
    height: Float,
    unit: String = "inch",
    top: String = "", // eg. "12in13" or ""
    middle: String = "",
    bottom: String = "",
    onChange: (name:String, s:String) -> Unit = {name,s ->}
){
    Column(
        modifier = Modifier
            .width(width.dp).height(height.dp)
    ) {
        Row(modifier = Modifier
            .weight(1.0F)
        ){
            ImperialLengthInput("Top", toImparialLength(top),
                onChange = {
                    onChange("top", toImparialLengthString(it))
                })
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1.0F)
        ){
            ImperialLengthInput("Middle", toImparialLength(middle),
                onChange = {
                    onChange("middle", toImparialLengthString(it))
                })
        }
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .weight(1.0F)
        ){
            ImperialLengthInput("Bottom", toImparialLength(bottom),
                onChange = {
                    onChange("bottom", toImparialLengthString(it))
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
fun PreviewWindowWidthInput(){
    SLTheme {
        WindowWidthInput(
            width = 360F,
            height = 400F
        )
    }
}