package com.feiyunative.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "focus_session",
    foreignKeys = [
        ForeignKey(
            entity = PlanItemEntity::class,
            parentColumns = ["id"],
            childColumns = ["planItemId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["planItemId"]),
        Index(value = ["startAt"])
    ]
)
data class FocusSessionEntity(
    @PrimaryKey
    val id: String,

    /** 关联的 PlanItem */
    val planItemId: String,

    val startAt: Long,
    val endAt: Long,

    /** 实际专注时长（毫秒） */
    val durationMillis: Long,

    /** 来源标记，方便未来迁移 */
    val source: String = "feiyu_quick"
)
