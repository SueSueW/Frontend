package com.feiyunative.data.dao

import androidx.room.*
import com.feiyunative.data.entity.PlanEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanDao {

    /** 顶部 Tab：只显示未归档的 Plan */
    @Query("""
        SELECT * FROM plan
        WHERE archived = 0
        ORDER BY position ASC
    """)
    fun observeActive(): Flow<List<PlanEntity>>

    @Query("""
        SELECT * FROM plan
        WHERE id = :id
        LIMIT 1
    """)
    suspend fun getById(id: String): PlanEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(plan: PlanEntity)

    /** 逻辑归档（不物理删除） */
    @Query("""
        UPDATE plan
        SET archived = 1
        WHERE id = :id
    """)
    suspend fun archive(id: String)

    @Delete
    suspend fun delete(plan: PlanEntity)
}
