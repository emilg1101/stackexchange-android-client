package com.github.emilg1101.stackexchangeapp.core.extensions

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

const val FORMAT_YYYY_MM_DD = "yyyy-MM-dd"
const val FORMAT_DD_MM_YYYY = "dd-MM-yyyy"
const val FORMAT_DDMMYYYY = "dd.MM.yyyy"

private val dateTimeFormatFactory = { format: String ->
    SimpleDateFormat(format, Locale.US)
}

fun Calendar.format(format: String = FORMAT_YYYY_MM_DD) = this.time.format(format)

fun String.toCalendar(format: String = FORMAT_YYYY_MM_DD): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = dateTimeFormatFactory.invoke(format).parse(this) ?: throw Exception()
    return calendar
}

fun String.toDate(format: String = FORMAT_YYYY_MM_DD) =
    if (this.isEmpty()) {
        null
    } else {
        this.toCalendar(format).time
    }

fun Date.format(format: String = FORMAT_DDMMYYYY): String = dateTimeFormatFactory.invoke(format).format(this)

fun Date?.toCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    if (this != null) {
        calendar.time = this
    }
    return calendar
}
