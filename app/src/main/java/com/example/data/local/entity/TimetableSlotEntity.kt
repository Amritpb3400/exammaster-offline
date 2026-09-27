package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timetable_slots")
data class TimetableSlotEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long,
    val dayOfWeek: String, // "Monday", "Tuesday", etc. or "Daily"
    val startTime: String, // e.g. "07:00 AM"
    val endTime: String,   // e.g. "09:00 AM"
    val subject: String,
    val topicNotes: String = "",
    val isReminderEnabled: Boolean = true,
    val isCompletedToday: Boolean = false
)
