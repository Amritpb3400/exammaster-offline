package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): UserEntity?

    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    fun getUserById(userId: Long): Flow<UserEntity?>

    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    suspend fun getUserByIdSync(userId: Long): UserEntity?

    @Query("SELECT * FROM users ORDER BY totalXp DESC")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity): Long

    @Update
    suspend fun updateUser(user: UserEntity)

    @Query("UPDATE users SET totalXp = totalXp + :xpToAdd WHERE id = :userId")
    suspend fun addXp(userId: Long, xpToAdd: Int)

    @Query("UPDATE users SET streak = :newStreak, bestStreak = CASE WHEN :newStreak > bestStreak THEN :newStreak ELSE bestStreak END, lastActiveDate = :dateStr WHERE id = :userId")
    suspend fun updateStreak(userId: Long, newStreak: Int, dateStr: String)
}
