package com.feiyunative.ui.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feiyunative.core.db.DbProvider
import com.feiyunative.data.dao.FocusSessionDao
import com.feiyunative.data.dao.PlanItemDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId

data class TodayItemStat(
    val planItemId: String,
    val title: String,
    val totalMillis: Long,
    val percent: Float
)

class TodaySummaryViewModel(app: Application) : AndroidViewModel(app){

    private val db = DbProvider.get(app)
    private val focusDao: FocusSessionDao = db.focusSessionDao()
    private val planItemDao: PlanItemDao = db.planItemDao()

    private val _totalMillis = MutableStateFlow(0L)
    val totalMillis: StateFlow<Long> = _totalMillis

    private val _items = MutableStateFlow<List<TodayItemStat>>(emptyList())
    val items: StateFlow<List<TodayItemStat>> = _items

    init {
        loadToday()
    }

    fun loadToday(date: LocalDate = LocalDate.now()) {
        viewModelScope.launch {
            val zone = ZoneId.systemDefault()
            val from = date.atStartOfDay(zone).toInstant().toEpochMilli()
            val to = from + 24 * 60 * 60 * 1000

            val raw = focusDao.sumByItemBetween(from, to)
            val total = raw.sumOf { it.totalMillis }

            _totalMillis.value = total

            if (total == 0L) {
                _items.value = emptyList()
                return@launch
            }

            val result = raw.map { row ->
                val item = planItemDao.getById(row.planItemId)
                TodayItemStat(
                    planItemId = row.planItemId,
                    title = item?.title ?: "Unknown",
                    totalMillis = row.totalMillis,
                    percent = row.totalMillis.toFloat() / total.toFloat()
                )
            }.sortedByDescending { it.totalMillis }

            _items.value = result
        }
    }
}
