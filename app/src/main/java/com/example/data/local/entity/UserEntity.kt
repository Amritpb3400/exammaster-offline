package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val username: String,
    val passwordHash: String,
    val displayName: String,
    val targetExam: String,
    val targetYear: String = "2026",
    val avatarId: Int = 0,
    val bio: String = "Striving for rank #1!",
    val streak: Int = 1,
    val bestStreak: Int = 1,
    val totalXp: Int = 0,
    val lastActiveDate: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
