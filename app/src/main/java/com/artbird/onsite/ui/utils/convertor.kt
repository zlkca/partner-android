package com.artbird.onsite.ui.utils

import com.artbird.onsite.domain.Address
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun convertToFloat(it: String): Float {
    return if (it.isNotEmpty()){
        var numeric = true

        try {
            java.lang.Float.parseFloat(it)
        } catch (e: NumberFormatException) {
            numeric = false
        }
        if(numeric) it.toFloat() else 0f
    }else{
        0f
    }
}

fun convertToInt(it: String): Int {
    return if (it.isNotEmpty()){
        var numeric = true

        try {
            Integer.parseInt(it)
        } catch (e: NumberFormatException) {
            numeric = false
        }
        if(numeric) it.toInt() else 0
    }else{
        0
    }
}

fun convertToDecimalStr(it: Float): String {
    val df = DecimalFormat("#.##")
    return df.format(it)
}

fun getDateTimeString(s: String): String {
    val list = s.split(".")
    return if(list.isNotEmpty()){
        list[0]
    }else{
        ""
    }

}

fun getDate(s: String): String {
    val a = s.split(" ")
    return a.get(0);
}
fun getTime(s: String): String {
    val a = s.split(" ")
    return if (a.size > 1) a.get(1) else "";
}

fun toLocalDateTime(s: String): LocalDateTime {
//    val a = s.split(" ");
//    val t = "${a[0]}T${a[1]}";
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return LocalDateTime.parse(s, formatter)
}

fun floatToString(it: Float): String {
    return if (it == 0f) {
        ""
    } else {
        it.toString()
    }
}

fun intToString(it: Int): String {
    return if (it == 0) {
        ""
    } else {
        it.toString()
    }
}

fun getAddressString(address: Address, withPostcode: Boolean = false): String {
    return if(withPostcode) "${address.unitNumber} ${address.streetNumber} ${address.streetName}, ${address.city} ${address.province} ${address.postcode}".trim()
    else "${address.unitNumber} ${address.streetNumber} ${address.streetName}, ${address.city} ${address.province}".trim()
}

fun getCurrentDateString(): String? {
    val dateTime =
        LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return dateTime.format(formatter)
}