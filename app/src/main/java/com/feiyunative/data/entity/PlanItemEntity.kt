package com.feiyunative.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "plan_item",
    foreignKeys = [
        ForeignKey(
            entity = PlanSectionEntity::class,
            parentColumns = ["id"],
            childColumns = ["sectionId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["sectionId"]),
        Index(value = ["sectionId", "createdAt"])
    ]
)
data class PlanItemEntity(
    @PrimaryKey
    val id: String,

    /** 所属 Section */
    val sectionId: String,

    val title: String,

    /**
     * timer | count | check
     * 直接用 String，避免 enum 迁移成本
     */
    val type: String,

    /** count 类型目标 */
    val target: Int?,

    /** count 已完成 */
    val completed: Int?,

    /** 是否完成（check / timer 可用） */
    val done: Boolean,

    val createdAt: Long
)
