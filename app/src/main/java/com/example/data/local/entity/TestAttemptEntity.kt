package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test_attempts")
data class TestAttemptEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long,
    val examCategory: String,
    val subjectFilter: String,
    val totalQuestions: Int,
    val correctCount: Int,
    val incorrectCount: Int,
    val unattemptedCount: Int,
    val accuracy: Float,
    val score: Int,
    val xpEarned: Int,
    val timeTakenSeconds: Int,
    val totalTimeSeconds: Int,
    val timestamp: Long = System.currentTimeMillis(),
    val dateFormatted: String
)
