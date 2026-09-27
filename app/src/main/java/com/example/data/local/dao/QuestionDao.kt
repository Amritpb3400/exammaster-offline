package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.QuestionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions")
    fun getAllQuestions(): Flow<List<QuestionEntity>>

    @Query("SELECT COUNT(*) FROM questions")
    suspend fun getQuestionCount(): Int

    @Query("SELECT DISTINCT examCategory FROM questions")
    fun getCategories(): Flow<List<String>>

    @Query("SELECT DISTINCT subject FROM questions WHERE (:category = 'All' OR examCategory = :category)")
    fun getSubjectsForCategory(category: String): Flow<List<String>>

    @Query("""
        SELECT * FROM questions 
        WHERE (:category = 'All' OR examCategory = :category)
        AND (:subject = 'All' OR subject = :subject)
        AND (:difficulty = 'All' OR difficulty = :difficulty)
        ORDER BY RANDOM() 
        LIMIT :limit
    """)
    suspend fun getQuizQuestions(
        category: String,
        subject: String,
        difficulty: String,
        limit: Int
    ): List<QuestionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(questions: List<QuestionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: QuestionEntity): Long
}
