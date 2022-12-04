package com.artbird.onsite.ui.window

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.artbird.onsite.ui.components.ImperialLengthInput
import com.artbird.onsite.ui.components.Title2

@Composable
fun WindowWidthForm(
     onChange: () -> Unit = {},
){
//    Column(
//    ) {
//        Title2(text = "Width")
//        ImperialLengthInput(
//            label = "Top",
//            len = width.top,
//            onChange = {
//                width = UIWindowWidth(it, width.mid, width.bottom)
//            }
//        )
////                ImperialLengthInput("Middle", width.mid, {it ->
////                    width = UIWindowWidth(width.top, it, width.bottom)
////                })
////                ImperialLengthInput("Bottom", width.bottom, {it ->
////                    width = UIWindowWidth(width.top, width.mid, it)
////                })}
//    }
}