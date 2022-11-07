package com.artbird.onsite.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.TabRowDefaults.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*


@Composable
fun CalendarNav(){
    val now = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = now.format(formatter)

    Row(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(date)
    }
}

@Composable
fun CalendarHead(){
    val now = LocalDate.now()
    val dow = now.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
    val formatter = DateTimeFormatter.ofPattern("d")
    val date = now.format(formatter)

    Row(){
        Box(modifier = Modifier.weight(1f))
        Box(modifier = Modifier
            .weight(3f)) {
                Column(
                    modifier=Modifier.padding(top=4.dp, bottom = 4.dp)
                ) {
                    Text(dow)
                    Text(date, fontSize = 28.sp)
                }
        }
    }
}

@Composable
fun CalendarBody(){
    Row() {
        Box(modifier = Modifier
            .weight(1f)
//            .border(1.dp, Color.Gray)
        ){
            Column() {
                Box(modifier = Modifier.height(32.dp)) {
                    Text("")
                }
                for( i in 7..12) {
                    Box(modifier=Modifier.height(48.dp).padding(start=20.dp, top = 8.dp)){
                        Text("$i AM")
                    }
                }
                for( i in 1..12) {
                    Box(modifier=Modifier.height(48.dp).padding(start=20.dp, top = 8.dp)){
                        Text("$i PM")
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .weight(3f)
                .border(1.dp, Color.Gray),
        ){
            Column(
                modifier = Modifier.background(Color.LightGray)
            ){
                for( i in 7..24) {
                    Divider(color = Color.Gray)
                    Box(modifier = Modifier.height(48.dp)) {
                        Text("")
                    }
                }
            }
        }
    }

}

@Composable
fun Calendar(){
    Column {
        CalendarNav()
        CalendarHead()
        CalendarBody()

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCalendar(){
    Calendar()
}