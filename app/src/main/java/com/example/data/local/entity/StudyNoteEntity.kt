package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "study_notes")
data class StudyNoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long,
    val title: String,
    val subject: String,
    val content: String,
    val isPinned: Boolean = false,
    val sourcePdfName: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)
