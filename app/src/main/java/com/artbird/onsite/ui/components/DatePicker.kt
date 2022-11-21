package com.artbird.onsite.ui.components

import android.app.DatePickerDialog
import android.widget.DatePicker

import android.content.Context
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

// Date should not be empty and by default should be today
@Composable
fun DatePicker(
    context: Context,
    label: String,
    date: String,
    onValueChange: (y: Int, m: Int, d:Int) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth().padding(8.dp),
) {

    var dateTime = LocalDateTime.now()
//    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    // load current data on UI
    if(date.isNotEmpty()) {
        val ts = date.split("-")
        val year=Integer.parseInt(ts[0]);
        val month=Integer.parseInt(ts[1]);
        var dayOfMonth = Integer.parseInt(ts[2])
        dateTime = LocalDateTime.now().withYear(year).withMonth(month).withDayOfMonth(dayOfMonth)
    }

    fun setDate(view: DatePicker, year: Int, month: Int, dayOfMonth: Int){
        onValueChange(year, month+1, dayOfMonth)
    }

    val datePickerDialog = DatePickerDialog(
        context,
        ::setDate,
        dateTime.year,
        dateTime.monthValue - 1,
        dateTime.dayOfMonth,
    )

    OutlinedTextField(
//        readOnly = true,
        modifier=modifier,
        value = date,
        onValueChange = {  },
        label = { Text(label) },
        trailingIcon = {
            IconButton(
                onClick = { datePickerDialog.show() }
            ) {
                Icon(Icons.Default.DateRange,contentDescription = null)
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDatePicker(){
    var date by remember { mutableStateOf("2022-08-23") }

    fun handleChange(y:Int, m: Int, d:Int){
        val dateTime = LocalDateTime.now().withYear(y).withMonth(m).withDayOfMonth(d)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        date = dateTime.format(formatter)
    }

    DatePicker(LocalContext.current, "Start Time", date, ::handleChange)
}