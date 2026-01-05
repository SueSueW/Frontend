package com.feiyunative.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "plan",
    indices = [
        Index(value = ["position"])
    ]
)
data class PlanEntity(
    @PrimaryKey
    val id: String,

    val title: String,

    /** 用于顶部 Tab 排序 */
    val position: Int,

    /** 创建时间（epoch millis） */
    val createdAt: Long,

    /** 是否归档（不直接删除） */
    val archived: Boolean
)
