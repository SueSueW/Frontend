package com.feiyunative.core.util

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

fun formatDateTime(millis: Long): String {
    return dateFormat.format(Date(millis))
}

fun formatDuration(millis: Long): String {
    val totalSeconds = TimeUnit.MILLISECONDS.toSeconds(millis)
    val h = totalSeconds / 3600
    val m = (totalSeconds % 3600) / 60
    val s = totalSeconds % 60

    return when {
        h > 0 -> String.format("%d:%02d:%02d", h, m, s)
        else -> String.format("%02d:%02d", m, s)
    }
}
