package com.artbird.onsite.ui.components

import android.app.TimePickerDialog
import android.content.Context
import android.widget.TimePicker
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun TimePicker(
       context: Context,
       label: String,
       time: String,
       onValueChange: (h: Int, m: Int) -> Unit,
       modifier: Modifier = Modifier.fillMaxWidth().padding(8.dp),
) {

    var dateTime = LocalDateTime.now()

    if(time.isNotEmpty()) {
        val ts = time.split(":")
        val hours=Integer.parseInt(ts[0]);
        val minutes=Integer.parseInt(ts[1]);
        dateTime = LocalDateTime.now().withHour(hours).withMinute(minutes)
    }

    fun setTime(view: TimePicker, hour: Int, minute: Int){
        onValueChange(hour, minute)
    }

    val timePickerDialog = TimePickerDialog(
        context,
        ::setTime,
        dateTime.hour,
        dateTime.minute,
        false
    )

    OutlinedTextField(
//        readOnly = true,
        value = time,
        onValueChange = {  },
        label = { Text(label) },
        modifier = modifier,
        trailingIcon = {
            IconButton(
                onClick = { timePickerDialog.show() }
            ) {
                Icon(Icons.Default.DateRange,contentDescription = null)
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTimePicker(){
    var time by remember { mutableStateOf("13:25") }

    fun handleChange(h:Int, m: Int){
        val dateTime = LocalDateTime.now().withHour(h).withMinute(m).withSecond(0);
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        time = dateTime.format(formatter)
    }

    TimePicker(LocalContext.current, "Start Time", time, ::handleChange)
}