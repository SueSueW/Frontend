package com.feiyunative.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.feiyunative.ui.vm.TodaySummaryViewModel
import kotlin.math.roundToInt

@Composable
fun TodaySummaryCard(
    vm: TodaySummaryViewModel = viewModel()
) {
    val totalMillis by vm.totalMillis.collectAsState()
    val items by vm.items.collectAsState()

    if (totalMillis == 0L) return

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "今日专注 ${formatMillis(totalMillis)}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            // ---------- 饼图 ----------
            Row {
                Canvas(
                    modifier = Modifier
                        .size(140.dp)
                ) {
                    var startAngle = -90f
                    val colors = listOf(
                        Color(0xFF4CAF50),
                        Color(0xFF2196F3),
                        Color(0xFFFFC107),
                        Color(0xFFF44336),
                        Color(0xFF9C27B0)
                    )

                    items.forEachIndexed { index, item ->
                        val sweep = item.percent * 360f
                        drawArc(
                            color = colors[index % colors.size],
                            startAngle = startAngle,
                            sweepAngle = sweep,
                            useCenter = true,
                            size = Size(size.width, size.height)
                        )
                        startAngle += sweep
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // ---------- 列表 ----------
                Column(modifier = Modifier.weight(1f)) {
                    items.forEachIndexed { index, item ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(item.title, modifier = Modifier.weight(1f))
                            Text(
                                "${formatMillis(item.totalMillis)} · ${(item.percent * 100).roundToInt()}%"
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                }
            }
        }
    }
}

private fun formatMillis(ms: Long): String {
    val totalMinutes = ms / 60000
    val h = totalMinutes / 60
    val m = totalMinutes % 60
    return if (h > 0) "${h}h ${m}m" else "${m}m"
}
