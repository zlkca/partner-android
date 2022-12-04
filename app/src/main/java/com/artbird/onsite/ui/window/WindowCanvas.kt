package com.artbird.onsite.ui.window

import android.content.res.Configuration
import androidx.compose.runtime.Composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.Window
import com.artbird.onsite.ui.theme.SLTheme

import androidx.compose.ui.tooling.preview.Devices
import com.artbird.onsite.domain.ImparialLength
import com.artbird.onsite.domain.WindowHeight
import com.artbird.onsite.domain.WindowWidth
import com.artbird.onsite.ui.components.ImperialLengthInput
import kotlin.math.roundToInt

const val StandardWidth = 700
const val StandardHeight = 1400
const val MaxCanvasWidth = 1200.0F
const val LeftSideWidth = 240

@Composable
fun WindowCanvas(
    numOfWindows: Int = 1,
    w: WindowWidth = WindowWidth(),
    h: WindowHeight = WindowHeight(),
    onChange: (name:String, s:String) -> Unit = {name,s ->}
){
    
    val padding = 20f
    var width = StandardWidth
    var totalWidth = (StandardWidth + 2 * padding)*numOfWindows
    if( totalWidth > MaxCanvasWidth){
        width = ( MaxCanvasWidth / numOfWindows - 2 * padding).roundToInt()
        totalWidth = MaxCanvasWidth
    }

    Column(modifier = Modifier
        .width((totalWidth + LeftSideWidth).toInt().dp)
        .padding(top= 35.dp, start = 25.dp)
    ) {
        Row( modifier = Modifier.fillMaxWidth()
//            .width((LeftSideWidth + MaxCanvasWidth).dp)
        ){
            Row(
                modifier = Modifier
                    .width(LeftSideWidth.dp)
            ){
                // padding area
            }

            WindowHeightInput(
                width = totalWidth.toInt(),
                unit = h.unit,
                left = h.left,
                middle = h.mid,
                right = h.right,
                onChange = onChange,
            )
        }


        Row(modifier = Modifier.height(StandardHeight.dp)) {
            Row(modifier = Modifier
                .width(LeftSideWidth.dp)
            ) {
                WindowWidthInput(
                    width = LeftSideWidth.toFloat(),
                    height = StandardHeight.toFloat(),
                    unit = w.unit,
                    top = w.top,
                    middle = w.mid,
                    bottom = w.bottom,
                    onChange = onChange,
                )
            }
            Row(modifier = Modifier
                .width(totalWidth.toInt().dp)
            ) {
                Canvas(modifier = Modifier.width(totalWidth.toInt().dp)) {
//                val canvasWidth = size.width // size.width
//                val canvasHeight = size.height

                    var start = 0F // (canvasWidth - totalWidth) / 2
                    drawRect(
                        topLeft = Offset(start, 20.0F),
                        size = Size(totalWidth, StandardHeight.toFloat()),
                        color = Color.Gray
                    )

                    repeat(numOfWindows) {
                        start = start + padding
                        drawRect(
                            topLeft = Offset(start, 20.0F + padding),
                            size = Size(width.toFloat(), StandardHeight - 2 * padding),
                            color = Color.White
                        )
                        start += width + padding
                    }
                }
            }
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
fun PreviewWindowCanvas(){
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
        WindowCanvas(
            numOfWindows = 2
        )
    }
}