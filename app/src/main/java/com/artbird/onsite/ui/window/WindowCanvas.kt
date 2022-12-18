package com.artbird.onsite.ui.window

import android.content.res.Configuration
import android.text.TextPaint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
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

fun getDirections(s: String): DirectionArray {
    if(s.isNotEmpty()) {
        val a = s.split("");
        val directions = ArrayList<String>();
        val ts = ArrayList<Int>();
        var index = 0;
        a.forEach {
            if (it !== "T") {
                directions.add(it)
                index++
            } else {
                ts.add(index)
            }
        };
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
    directions: String = "",
    onChange: (name: String, s: String) -> Unit = { name, s -> },
){
    
    val padding = 20f
    var width = StandardWidth
    var totalWidth = (StandardWidth + 2 * padding)*numOfWindows
    if( totalWidth > MaxCanvasWidth){
        width = ( MaxCanvasWidth / numOfWindows - 2 * padding).roundToInt()
        totalWidth = MaxCanvasWidth
    }

    val directionArray = getDirections(directions);
    val textMeasure = rememberTextMeasurer()
    val paint = TextPaint();
//    mTextPaint.setTextSize(16 * getResources().getDisplayMetrics().density);
//    paint.setColor(MaterialTheme.colorScheme.onBackground);

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

                    repeat(numOfWindows) { index ->
                        start = start + padding
                        drawRect(
                            topLeft = Offset(start, 20.0F + padding),
                            size = Size(width.toFloat(), StandardHeight - 2 * padding),
                            color = Color.White
                        )

                        if(directionArray.directions.size > index){

//                            drawText(
//                                textMeasurer = textMeasure,
//                                text = "Hello", // directionArray.directions[index],
//                                topLeft = Offset(100.dp.toPx(), 200.dp.toPx())
////                                topLeft = Offset((start).dp.toPx(), (200).dp.toPx())
//                            )

//                            drawContext.canvas.nativeCanvas.apply {
//                                drawText(
//                                    "Hey, Himanshu",
//                                    size.width / 2,
//                                    size.height / 2,
////                                    Paint().apply {
////                                        textSize = 100
////                                        color = Color.BLUE
////                                        textAlign = Paint.Align.CENTER
////                                    }
//                                )
//                            }

//                            drawText(
//                                "Hey, Himanshu",
//                                size.width / 2,
//                                size.height / 2,
//                            )
                        }

                        start += width + padding
                    }

//                    drawText(
//                        textMeasurer = textMeasure,
//                        text = "Hello world1",
//                        topLeft = Offset(10.dp.toPx(), 10.dp.toPx())
//                    )
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
            numOfWindows = 2,
            directions = "LRTR"
        )
    }
}