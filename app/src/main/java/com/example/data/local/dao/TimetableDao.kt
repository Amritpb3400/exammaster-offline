package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.local.entity.TimetableSlotEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TimetableDao {
    @Query("SELECT * FROM timetable_slots WHERE userId = :userId ORDER BY id ASC")
    fun getAllSlots(userId: Long): Flow<List<TimetableSlotEntity>>

    @Query("SELECT * FROM timetable_slots WHERE userId = :userId AND (dayOfWeek = :day OR dayOfWeek = 'Daily')")
    fun getSlotsForDay(userId: Long, day: String): Flow<List<TimetableSlotEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSlot(slot: TimetableSlotEntity): Long

    @Update
    suspend fun updateSlot(slot: TimetableSlotEntity)

    @Delete
    suspend fun deleteSlot(slot: TimetableSlotEntity)

    @Query("UPDATE timetable_slots SET isCompletedToday = :completed WHERE id = :slotId")
    suspend fun toggleSlotCompletion(slotId: Long, completed: Boolean)

    @Query("UPDATE timetable_slots SET isCompletedToday = 0 WHERE userId = :userId")
    suspend fun resetDailyCompletion(userId: Long)
}
