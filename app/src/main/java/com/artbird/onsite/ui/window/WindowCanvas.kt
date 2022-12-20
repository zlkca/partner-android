package com.artbird.onsite.ui.window

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.theme.SLTheme
import kotlin.math.roundToInt

const val StandardWidth = 600
const val StandardHeight = 900
const val MaxCanvasWidth = 1400.0F
const val LeftSideWidth = 240
const val padding = 20f

fun getDirections(s: String): DirectionArray {
    if(s.isNotEmpty()) {
        val a = s.trim().split("").filter{ it -> it.isNotEmpty()};
//        Log.d("zlk", a.toString())
        val directions = ArrayList<String>();
        val ts = ArrayList<Int>();
        var index = 0;
        a.forEach {
            if (it != "T") {
                directions.add(it)
                index++
            } else {
                ts.add(index)
            }
        };
//        Log.d("zlk", directions.toString())
//        Log.d("zlk", ts.toString())
        return DirectionArray(directions, ts)
    }else{
        return DirectionArray();
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun WindowCanvas(
    numOfWindows: Int = 1,
    w: WindowWidth = WindowWidth(),
    h: WindowHeight = WindowHeight(),
    frameStyles: WindowFrameStyles = WindowFrameStyles(),
    dividerRail: WindowDividerRail = WindowDividerRail(),
    directions: String = "",
    onChange: (name: String, s: String) -> Unit = { name, s -> },
){
    

    var width = StandardWidth
    var totalWidth = (StandardWidth + 2 * padding)*numOfWindows
    if( totalWidth > MaxCanvasWidth){
        width = ( MaxCanvasWidth / numOfWindows - 2 * padding).roundToInt()
        totalWidth = MaxCanvasWidth
    }

    val directionArray = getDirections(directions);
    val paint = android.graphics.Paint();
    paint.color = 0xff1c480a.toInt() // colorScheme.onBackground
    paint.textSize = 56f

    Column(modifier = Modifier
        .width((totalWidth + LeftSideWidth).toInt().dp)
        .padding(top = 35.dp, start = 25.dp)
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
                .width(MaxCanvasWidth.toInt().dp)
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




                    start = 0F
                    var tsIndex = 0
                    repeat(numOfWindows) { index ->
                        start = start + padding
                        drawRect(
                            topLeft = Offset(start, 20.0F + padding),
                            size = Size(width.toFloat(), StandardHeight - 2 * padding),
                            color = Color.White
                        )

//                        Log.d("zlk", "index: ${index}")
//                        Log.d("zlk", "direction: ${directionArray.directions[index]}")
                        if(directionArray.directions.size > index){
                            this.drawIntoCanvas {
                                it.nativeCanvas.drawText(
                                    directionArray.directions[index],
                                    start + (width/2),
                                    700f,
                                    paint
                                )
                            }
                        }

                        if(tsIndex < directionArray.ts.size && directionArray.ts[tsIndex] == index){
                            this.drawIntoCanvas {
                                it.nativeCanvas.drawText(
                                    "T",
                                    start - 2 * padding,
                                    700f,
                                    paint
                                )
                            }
                            tsIndex++
                        }

                        start += width + padding
                    }


                    this.drawIntoCanvas {
                        it.nativeCanvas.drawText(
                            frameStyles.left,
                            padding/2,
                            StandardHeight.toFloat()/2,
                            paint
                        )

                        it.nativeCanvas.drawText(
                            frameStyles.right,
                            totalWidth - padding - 100,
                            StandardHeight.toFloat()/2,
                            paint
                        )

                        it.nativeCanvas.drawText(
                            frameStyles.top,
                            totalWidth/2,
                            padding/2 + 60,
                            paint
                        )
                        it.nativeCanvas.drawText(
                            frameStyles.bottom,
                            totalWidth/2,
                            StandardHeight - padding/2,
                            paint
                        )
                    }
                }
            }
        }
    }

}


@Preview(
//    name="Light Mode",
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.AUTOMOTIVE_1024p,
    widthDp = 2560, heightDp = 1600
)
@Preview(showBackground = true)
@Composable
fun PreviewWindowCanvas(){

    SLTheme {
        WindowCanvas(
            numOfWindows = 3,
            directions = "LRTR",
            frameStyles = WindowFrameStyles("Z", "bigZ", "L", "HG")
        )
    }
}