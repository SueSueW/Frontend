package com.feiyunative.ui.screen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.feiyunative.ui.component.TodaySummaryCard
import com.feiyunative.ui.vm.FocusTimelineViewModel
import com.feiyunative.data.entity.FocusSessionEntity
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import android.app.Application
import android.app.DatePickerDialog
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FocusTimelineScreen() {
    val context = LocalContext.current
    val vm: FocusTimelineViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory.getInstance(
            context.applicationContext as Application
        )
    )

    val sessions by vm.sessions.collectAsState()
    val selectedDate by vm.selectedDate.collectAsState()

    var showEditDialog by remember { mutableStateOf(false) }
    var editingSession by remember { mutableStateOf<FocusSessionEntity?>(null) }

    Column(modifier = Modifier.fillMaxSize()) {

        // ---------- 今日总结 ----------
        TodaySummaryCard()

        // ---------- 日期切换（MVP：前后一天 + 点击打开日期选择） ----------
        val dateFormatter = remember { DateTimeFormatter.ofPattern("yyyy-MM-dd") }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = { vm.selectDate(selectedDate.minusDays(1)) }) {
                Text("← 前一天")
            }

            TextButton(
                onClick = {
                    val y = selectedDate.year
                    val m = selectedDate.monthValue - 1
                    val d = selectedDate.dayOfMonth
                    DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            vm.selectDate(LocalDate.of(year, month + 1, dayOfMonth))
                        },
                        y,
                        m,
                        d
                    ).show()
                }
            ) {
                Text(selectedDate.format(dateFormatter))
            }

            TextButton(onClick = { vm.selectDate(selectedDate.plusDays(1)) }) {
                Text("后一天 →")
            }
        }

        // ---------- 追加时间段 ----------
        Button(
            onClick = {
                editingSession = null
                showEditDialog = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text("+ 追加时间段")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // ---------- 时间线 ----------
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 84.dp)
        ) {
            items(sessions, key = { it.id }) { session ->
                FocusSessionRow(
                    session = session,
                    onEdit = {
                        editingSession = session
                        showEditDialog = true
                    },
                    onDelete = {
                        vm.deleteSession(session.id)
                    }
                )
            }
        }
    }

    // ---------- 编辑 / 新增 Dialog ----------
    if (showEditDialog) {
        FocusSessionEditDialog(
            session = editingSession,
            date = selectedDate,
            onConfirm = { startAt, endAt ->
                vm.saveSession(editingSession, startAt, endAt)
                showEditDialog = false
            },
            onDismiss = { showEditDialog = false }
        )
    }
}
