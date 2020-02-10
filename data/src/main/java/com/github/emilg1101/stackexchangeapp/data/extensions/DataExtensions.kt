package com.github.emilg1101.stackexchangeapp.data.extensions

import java.util.Calendar
import java.util.Date

fun Long.toDate() = Date(this)

fun Long.toCalendar(): Calendar = Calendar.getInstance().also { calendar ->
    calendar.timeInMillis = this * 1000
}
