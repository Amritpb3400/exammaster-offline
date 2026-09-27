package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.TestAttemptEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TestAttemptDao {
    @Query("SELECT * FROM test_attempts WHERE userId = :userId ORDER BY timestamp DESC")
    fun getAttemptsForUser(userId: Long): Flow<List<TestAttemptEntity>>

    @Query("SELECT * FROM test_attempts WHERE userId = :userId ORDER BY timestamp DESC LIMIT 5")
    fun getRecentAttemptsForUser(userId: Long): Flow<List<TestAttemptEntity>>

    @Query("SELECT COUNT(*) FROM test_attempts WHERE userId = :userId")
    fun getTotalAttemptsCount(userId: Long): Flow<Int>

    @Query("SELECT AVG(accuracy) FROM test_attempts WHERE userId = :userId")
    fun getAverageAccuracy(userId: Long): Flow<Float?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttempt(attempt: TestAttemptEntity): Long

    @Query("DELETE FROM test_attempts WHERE userId = :userId")
    suspend fun clearAttemptsForUser(userId: Long)
}
