package com.feiyunative.ui.screen

import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.feiyunative.data.entity.FocusSessionEntity

@Composable
fun EditSessionDialog(
    session: FocusSessionEntity,
    onDismiss: () -> Unit,
    onConfirm: (FocusSessionEntity) -> Unit
) {
    var secondsText by remember {
        mutableStateOf((session.durationMillis / 1000).toString())
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("修改专注时长（秒）") },
        text = {
            OutlinedTextField(
                value = secondsText,
                onValueChange = { secondsText = it },
                label = { Text("时长（秒）") }
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val seconds = secondsText.toLongOrNull() ?: return@TextButton
                    onConfirm(
                        session.copy(
                            durationMillis = seconds * 1000,
                            endAt = session.startAt + seconds * 1000
                        )
                    )
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
