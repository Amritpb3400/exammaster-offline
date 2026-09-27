package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val examCategory: String, // "UPSC", "SSC CGL", "Banking", "Railways", "General"
    val subject: String,      // "Indian Polity", "History", "Quantitative Aptitude", etc.
    val topic: String,
    val difficulty: String,   // "Easy", "Medium", "Hard"
    val questionText: String,
    val optionA: String,
    val optionB: String,
    val optionC: String,
    val optionD: String,
    val correctOption: Int,   // 0 for A, 1 for B, 2 for C, 3 for D
    val explanation: String,
    val isCustom: Boolean = false
)
