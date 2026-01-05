package com.feiyunative.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "plan_section",
    foreignKeys = [
        ForeignKey(
            entity = PlanEntity::class,
            parentColumns = ["id"],
            childColumns = ["planId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["planId"]),
        Index(value = ["planId", "position"])
    ]
)
data class PlanSectionEntity(
    @PrimaryKey
    val id: String,

    /** 所属 Plan */
    val planId: String,

    val title: String,

    /** Section 在 Plan 内排序 */
    val position: Int,

    /** 是否折叠 */
    val collapsed: Boolean
)
