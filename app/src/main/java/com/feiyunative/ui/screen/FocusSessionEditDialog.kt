package com.feiyunative.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.feiyunative.data.entity.FocusSessionEntity
import java.time.*
import java.time.format.DateTimeFormatter

@Composable
fun FocusSessionEditDialog(
    session: FocusSessionEntity?,
    date: LocalDate,
    onConfirm: (startAt: Long, endAt: Long) -> Unit,
    onDismiss: () -> Unit
) {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")

    // ---------- 初始值 ----------
    val initialStart = session?.startAt?.let {
        Instant.ofEpochMilli(it)
            .atZone(ZoneId.systemDefault())
            .toLocalTime()
    } ?: LocalTime.of(9, 0)

    val initialEnd = session?.endAt?.let {
        Instant.ofEpochMilli(it)
            .atZone(ZoneId.systemDefault())
            .toLocalTime()
    } ?: initialStart.plusMinutes(30)

    var startTime by remember { mutableStateOf(initialStart) }
    var endTime by remember { mutableStateOf(initialEnd) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(if (session == null) "新增时间段" else "修改时间段")
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                Text("日期：${date}")

                // ---------- 开始时间 ----------
                TimeField(
                    label = "开始时间",
                    time = startTime,
                    onTimeChange = { startTime = it }
                )

                // ---------- 结束时间 ----------
                TimeField(
                    label = "结束时间",
                    time = endTime,
                    onTimeChange = { endTime = it }
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val startAt = date
                        .atTime(startTime)
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
                        .toEpochMilli()

                    val endAt = date
                        .atTime(endTime)
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
                        .toEpochMilli()

                    onConfirm(startAt, endAt)
                }
            ) {
                Text("保存")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("取消")
            }
        }
    )
}

@Composable
private fun TimeField(
    label: String,
    time: LocalTime,
    onTimeChange: (LocalTime) -> Unit
) {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")

    var hour by remember { mutableStateOf(time.hour.toString().padStart(2, '0')) }
    var minute by remember { mutableStateOf(time.minute.toString().padStart(2, '0')) }

    Column {
        Text(label, style = MaterialTheme.typography.labelMedium)

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = hour,
                onValueChange = {
                    hour = it.take(2)
                    val h = it.toIntOrNull()
                    val m = minute.toIntOrNull()
                    if (h != null && m != null) {
                        onTimeChange(LocalTime.of(h.coerceIn(0, 23), m.coerceIn(0, 59)))
                    }
                },
                label = { Text("时") },
                modifier = Modifier.width(80.dp)
            )

            OutlinedTextField(
                value = minute,
                onValueChange = {
                    minute = it.take(2)
                    val h = hour.toIntOrNull()
                    val m = it.toIntOrNull()
                    if (h != null && m != null) {
                        onTimeChange(LocalTime.of(h.coerceIn(0, 23), m.coerceIn(0, 59)))
                    }
                },
                label = { Text("分") },
                modifier = Modifier.width(80.dp)
            )
        }
    }
}
