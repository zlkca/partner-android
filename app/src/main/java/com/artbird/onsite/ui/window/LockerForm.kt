package com.artbird.onsite.ui.window

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.ImparialLength
import com.artbird.onsite.domain.Locker
import com.artbird.onsite.ui.components.ImperialLengthInput
import com.artbird.onsite.ui.components.Input

fun toInchString( value: Int): String {
    val inches = value / 12
    val leftover = value % 12
    if(leftover == 0){
        return "${inches}\""
    }else{
        return "${inches}\" ${leftover}/16"
    }
}


@Composable
fun LockerForm(
    locker: Locker,
    onPositionChange: (name:String, pos: String) -> Unit,
    onSizeChange: (name:String, len: ImparialLength) -> Unit,
){
    Column(modifier = Modifier.padding(bottom = 8.dp)) {
        Row(
            modifier = Modifier.padding(top = 8.dp, bottom=8.dp)
        ) {
            Input(
                value = locker.position.vertical,
                onValueChange = {p -> onPositionChange("vertical", p)},
                label = "Vertical Position",
                modifier = Modifier.weight(1f).padding(start=8.dp, end = 16.dp)
            )

            Input(
                value = locker.position.horizontal,
                onValueChange = {p -> onPositionChange("horizontal", p)},
                label = "Horizontal Position",
                modifier = Modifier.weight(1f).padding(start = 16.dp, end=8.dp)
            )
        }

//        var left = toImparialLength(locker.size.left)
//        ImperialLengthInput("Left", left, {
//                it -> onSizeChange("left", it)}
//        )
//        var right = toImparialLength(locker.size.right)
//        ImperialLengthInput("Right", right, {it-> onSizeChange("right", it)})
//        var top = toImparialLength(locker.size.top)
//        ImperialLengthInput("Top", top, {it -> onSizeChange("top", it)})
//        var bottom = toImparialLength(locker.size.bottom)
//        ImperialLengthInput("Bottom", bottom, {it -> onSizeChange("bottom", it)})
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLockerForm(){
    var locker by remember { mutableStateOf(Locker()) }

    fun handleChange(name:String, len: ImparialLength){

    }
    fun handlePositionChange(name:String, p: String){

    }
    LockerForm(
        locker,
        ::handlePositionChange,
        ::handleChange,
        )
}