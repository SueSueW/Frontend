package com.feiyunative.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.feiyunative.data.entity.FocusSessionEntity
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.remember


@Composable
fun FocusSessionRow(
    session: FocusSessionEntity,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    val formatter = remember {
        SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    }

    val start = formatter.format(Date(session.startAt))
    val end = formatter.format(Date(session.endAt))
    val durationSec = session.durationMillis / 1000

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onEdit() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "$start → $end",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "时长：${durationSec}s",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "编辑",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(end = 12.dp)
            )
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "删除"
                )
            }
        }
    }
}
