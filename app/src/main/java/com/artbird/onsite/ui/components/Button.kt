package com.artbird.onsite.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SmallButton(
    label: String,
//    modifier: Modifier = Modifier
//        .padding(0.dp),
//        .focusTarget(),
    onClick: () -> Unit = {},
){
    Button(
//        shape = MaterialTheme.shapes.medium,
//        modifier = modifier,
        onClick = onClick
    ) {
        Text(text = label)
    }
}

@Composable
fun LongButton(
    label: String,
    modifier: Modifier = Modifier
        .padding(8.dp)
        .focusTarget(),
    onClick: () -> Unit = {},
){
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(5.dp),
    ) {
        Text(text = label)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSmallButton(){
    Column() {
        SmallButton(label = "Hello")
        LongButton(label = "Hello")
    }
}