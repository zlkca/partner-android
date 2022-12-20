package com.artbird.onsite.ui.window

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.WindowFrameStyles
import com.artbird.onsite.ui.components.Label2
import com.artbird.onsite.ui.components.Label3
import com.artbird.onsite.ui.components.OptionItem
import com.artbird.onsite.ui.components.Select
import com.artbird.onsite.ui.theme.SLTheme

@Composable
fun FrameStyleSelect(
    frameStyle: WindowFrameStyles = WindowFrameStyles(),
    onChange: (style: WindowFrameStyles) -> Unit = {},
    modifier: Modifier = Modifier.padding(8.dp)
){
    var frameOptions by remember { mutableStateOf(listOf<OptionItem>(
        OptionItem("Z", "Z"),
        OptionItem("bigZ", "bigZ"),
        OptionItem("HG", "HG"),
        OptionItem("L", "L"),
    )) }


    Column(modifier = modifier) {
        Label2(text = "Frame Styles")
        Row(modifier = Modifier.padding(bottom = 8.dp)) {
            Select(
                value = frameStyle.left,
                onValueChange ={
                    onChange(frameStyle.copy(left=it.value))
                },
                label = "Left",
                options = frameOptions,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
            )
            Select(
                value = frameStyle.right,
                onValueChange ={
                    onChange(frameStyle.copy(right=it.value))
                },
                label = "Right",
                options = frameOptions,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp)
            )
        }
        Row() {
            Select(
                value = frameStyle.top,
                onValueChange ={
                    onChange(frameStyle.copy(top=it.value))
                },
                label = "Top",
                options = frameOptions,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
            )
            Select(
                value = frameStyle.bottom,
                onValueChange ={
                    onChange(frameStyle.copy(bottom=it.value))
                },
                label = "Bottom",
                options = frameOptions,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp)
            )
        }
    }
}

@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewFrameStyleSelect(){

    SLTheme {
        FrameStyleSelect(
            WindowFrameStyles("Z", "bigZ", "L", "HG")
        )
    }
}