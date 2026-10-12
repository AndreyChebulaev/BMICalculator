package com.example.bodymassindexcalculator.utils

import android.annotation.SuppressLint
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime

fun Instant.formatBMIDate(timeZone: TimeZone = TimeZone.currentSystemDefault()): String {

    val now = Clock.System.now().toLocalDateTime(timeZone).date
    val target = this.toLocalDateTime(timeZone)

    val time = formatTime(target)
    val date = formatDate(target.date)

    return when (target.date) {
        now -> "Сегодня, $time"
        now.minus( period = DatePeriod(days = 1)) -> "Вчера, $time"
        else -> "$date, $time"
    }
}

@SuppressLint("DefaultLocale")
private fun formatTime(ldt: LocalDateTime): String {
    return String.format( "%02d:%02d", ldt.hour, ldt.minute)
}

@SuppressLint("DefaultLocale")
private fun formatDate(date: LocalDate): String {
    return String.format("%02d.%02d.%04d", date.dayOfMonth, date.monthNumber, date.year)
}