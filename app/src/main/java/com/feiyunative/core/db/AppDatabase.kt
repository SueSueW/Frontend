package com.feiyunative.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.feiyunative.data.dao.*
import com.feiyunative.data.entity.*

@Database(
    entities = [
        PlanEntity::class,
        PlanSectionEntity::class,
        PlanItemEntity::class,
        FocusSessionEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun planDao(): PlanDao
    abstract fun planSectionDao(): PlanSectionDao
    abstract fun planItemDao(): PlanItemDao
    abstract fun focusSessionDao(): FocusSessionDao
}
