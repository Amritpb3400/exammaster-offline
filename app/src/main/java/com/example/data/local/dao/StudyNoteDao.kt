package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.local.entity.StudyNoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StudyNoteDao {
    @Query("SELECT * FROM study_notes WHERE userId = :userId ORDER BY isPinned DESC, timestamp DESC")
    fun getAllNotes(userId: Long): Flow<List<StudyNoteEntity>>

    @Query("SELECT * FROM study_notes WHERE userId = :userId AND subject = :subject ORDER BY isPinned DESC, timestamp DESC")
    fun getNotesBySubject(userId: Long, subject: String): Flow<List<StudyNoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: StudyNoteEntity): Long

    @Update
    suspend fun updateNote(note: StudyNoteEntity)

    @Delete
    suspend fun deleteNote(note: StudyNoteEntity)

    @Query("UPDATE study_notes SET isPinned = :pinned WHERE id = :noteId")
    suspend fun togglePin(noteId: Long, pinned: Boolean)
}
