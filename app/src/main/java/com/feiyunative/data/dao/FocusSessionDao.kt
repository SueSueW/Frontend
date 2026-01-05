package com.feiyunative.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.feiyunative.data.entity.FocusSessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FocusSessionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(session: FocusSessionEntity)

    /**
     * ✅ Service 停止时更新 session 结束时间。
     * 说明：为了避免 Room migration（endAt nullable），进行中 session 先写 endAt=startAt。
     */
    @Query(
        """
        UPDATE focus_session
        SET endAt = :endAt,
            durationMillis = :durationMillis,
            source = :source
        WHERE id = :id
        """
    )
    suspend fun updateEndAndDuration(
        id: String,
        endAt: Long,
        durationMillis: Long,
        source: String
    )

    @Query("DELETE FROM focus_session WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("""
        SELECT * FROM focus_session
        WHERE planItemId = :planItemId
        ORDER BY startAt DESC
    """)
    fun observeByItem(planItemId: String): Flow<List<FocusSessionEntity>>

    @Query("""
        SELECT * FROM focus_session
        ORDER BY startAt DESC
        LIMIT :limit
    """)
    fun observeLatest(limit: Int = 200): Flow<List<FocusSessionEntity>>

    @Query("""
        SELECT * FROM focus_session
        WHERE startAt BETWEEN :from AND :to
        ORDER BY startAt DESC
    """)
    fun observeBetween(from: Long, to: Long): Flow<List<FocusSessionEntity>>

    @Query("""
        SELECT planItemId, SUM(durationMillis) as totalMillis
        FROM focus_session
        WHERE startAt BETWEEN :from AND :to
        GROUP BY planItemId
    """)
    suspend fun sumByItemBetween(
        from: Long,
        to: Long
    ): List<ItemDurationSum>

    @Query("""
        SELECT SUM(durationMillis)
        FROM focus_session
        WHERE startAt BETWEEN :from AND :to
    """)
    suspend fun sumDurationBetween(from: Long, to: Long): Long?
}

data class ItemDurationSum(
    val planItemId: String,
    val totalMillis: Long
)
