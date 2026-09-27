package com.example.data.repository

import android.content.Context
import com.example.data.local.AppDatabase
import com.example.data.local.InitialData
import com.example.data.local.entity.QuestionEntity
import com.example.data.local.entity.StudyNoteEntity
import com.example.data.local.entity.TestAttemptEntity
import com.example.data.local.entity.TimetableSlotEntity
import com.example.data.local.entity.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ExamRepository(
    private val database: AppDatabase,
    context: Context
) {

    private val userDao = database.userDao()
    private val questionDao = database.questionDao()
    private val testAttemptDao = database.testAttemptDao()
    private val timetableDao = database.timetableDao()
    private val studyNoteDao = database.studyNoteDao()

    // Small local prefs store, used only to remember which account was last
    // signed in so the app can resume the right session on next launch
    // (previously this always resumed user id 1, which was wrong on any
    // device with more than one registered account).
    private val prefs = context.applicationContext
        .getSharedPreferences("exam_prep_session", Context.MODE_PRIVATE)

    suspend fun initializeDatabaseIfNeeded() = withContext(Dispatchers.IO) {
        val count = questionDao.getQuestionCount()
        if (count == 0) {
            questionDao.insertQuestions(InitialData.initialQuestions)
        }
    }

    fun getLastLoggedInUserId(): Long? {
        val id = prefs.getLong(KEY_LAST_USER_ID, -1L)
        return if (id == -1L) null else id
    }

    private fun setLastLoggedInUserId(userId: Long?) {
        prefs.edit().apply {
            if (userId == null) remove(KEY_LAST_USER_ID) else putLong(KEY_LAST_USER_ID, userId)
        }.apply()
    }

    fun clearSession() {
        setLastLoggedInUserId(null)
    }

    // Authentication & Profile
    suspend fun login(username: String, password: String): Result<UserEntity> = withContext(Dispatchers.IO) {
        val user = userDao.getUserByUsername(username.trim())
        if (user == null) {
            return@withContext Result.failure(Exception("Account not found. Please register!"))
        }
        val enteredHash = hashPassword(password.trim())
        // Support accounts created before password hashing was introduced,
        // where passwordHash still holds the raw password.
        if (user.passwordHash != enteredHash && user.passwordHash != password.trim()) {
            return@withContext Result.failure(Exception("Incorrect password. Please try again."))
        }
        if (user.passwordHash != enteredHash) {
            // Transparently upgrade legacy plaintext accounts to a hashed password.
            userDao.updateUser(user.copy(passwordHash = enteredHash))
        }
        setLastLoggedInUserId(user.id)
        Result.success(user)
    }

    suspend fun register(
        username: String,
        password: String,
        displayName: String,
        targetExam: String,
        targetYear: String,
        avatarId: Int,
        bio: String
    ): Result<UserEntity> = withContext(Dispatchers.IO) {
        val existing = userDao.getUserByUsername(username.trim())
        if (existing != null) {
            return@withContext Result.failure(Exception("Username already taken. Please choose another!"))
        }
        val todayStr = getCurrentDateStr()
        val newUser = UserEntity(
            username = username.trim(),
            passwordHash = hashPassword(password.trim()),
            displayName = displayName.ifBlank { username.trim() },
            targetExam = targetExam,
            targetYear = targetYear,
            avatarId = avatarId,
            bio = bio,
            streak = 1,
            bestStreak = 1,
            totalXp = 50, // Welcome bonus XP!
            lastActiveDate = todayStr
        )
        val newId = userDao.insertUser(newUser)
        val created = newUser.copy(id = newId)

        // Seed initial timetable and notes for the new user
        InitialData.getSampleSlots(newId).forEach { timetableDao.insertSlot(it) }
        InitialData.getSampleNotes(newId).forEach { studyNoteDao.insertNote(it) }

        setLastLoggedInUserId(newId)
        Result.success(created)
    }

    private fun hashPassword(password: String): String {
        val digest = MessageDigest.getInstance("SHA-256").digest(password.toByteArray(Charsets.UTF_8))
        return digest.joinToString("") { "%02x".format(it) }
    }

    companion object {
        private const val KEY_LAST_USER_ID = "last_user_id"
    }

    fun observeUser(userId: Long): Flow<UserEntity?> = userDao.getUserById(userId)

    suspend fun getUserSync(userId: Long): UserEntity? = withContext(Dispatchers.IO) {
        userDao.getUserByIdSync(userId)
    }

    suspend fun updateUser(user: UserEntity) = withContext(Dispatchers.IO) {
        userDao.updateUser(user)
    }

    suspend fun addXp(userId: Long, xp: Int) = withContext(Dispatchers.IO) {
        userDao.addXp(userId, xp)
    }

    suspend fun recordActivityAndStreak(userId: Long): Int = withContext(Dispatchers.IO) {
        val user = userDao.getUserByIdSync(userId) ?: return@withContext 1
        val today = getCurrentDateStr()
        val lastDate = user.lastActiveDate

        if (lastDate == today) {
            return@withContext user.streak
        }

        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_YEAR, -1)
        val yesterday = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(cal.time)

        val newStreak = if (lastDate == yesterday) {
            user.streak + 1
        } else {
            1
        }

        userDao.updateStreak(userId, newStreak, today)
        newStreak
    }

    // Questions & Practice
    fun getCategories(): Flow<List<String>> = questionDao.getCategories()

    fun getSubjectsForCategory(category: String): Flow<List<String>> =
        questionDao.getSubjectsForCategory(category)

    suspend fun getQuestionsForQuiz(
        category: String,
        subject: String,
        difficulty: String,
        limit: Int
    ): List<QuestionEntity> = withContext(Dispatchers.IO) {
        var questions = questionDao.getQuizQuestions(category, subject, difficulty, limit)
        // If not enough questions match specific filters, fallback to all category or general questions
        if (questions.size < limit) {
            val fallback = questionDao.getQuizQuestions("All", "All", "All", limit)
            val combined = (questions + fallback).distinctBy { it.id }
            questions = combined.take(limit)
        }
        questions
    }

    suspend fun syncNewQuestions(): Int = withContext(Dispatchers.IO) {
        questionDao.insertQuestions(InitialData.syncPackQuestions)
        InitialData.syncPackQuestions.size
    }

    suspend fun addCustomQuestion(question: QuestionEntity): Long = withContext(Dispatchers.IO) {
        questionDao.insertQuestion(question)
    }

    // Test Attempts
    suspend fun saveTestAttempt(attempt: TestAttemptEntity): Long = withContext(Dispatchers.IO) {
        testAttemptDao.insertAttempt(attempt)
    }

    fun getTestAttempts(userId: Long): Flow<List<TestAttemptEntity>> =
        testAttemptDao.getAttemptsForUser(userId)

    fun getRecentAttempts(userId: Long): Flow<List<TestAttemptEntity>> =
        testAttemptDao.getRecentAttemptsForUser(userId)

    fun getTotalAttemptsCount(userId: Long): Flow<Int> =
        testAttemptDao.getTotalAttemptsCount(userId)

    fun getAverageAccuracy(userId: Long): Flow<Float?> =
        testAttemptDao.getAverageAccuracy(userId)

    // Timetable
    fun getTimetableSlots(userId: Long): Flow<List<TimetableSlotEntity>> =
        timetableDao.getAllSlots(userId)

    suspend fun insertSlot(slot: TimetableSlotEntity): Long = withContext(Dispatchers.IO) {
        timetableDao.insertSlot(slot)
    }

    suspend fun updateSlot(slot: TimetableSlotEntity) = withContext(Dispatchers.IO) {
        timetableDao.updateSlot(slot)
    }

    suspend fun deleteSlot(slot: TimetableSlotEntity) = withContext(Dispatchers.IO) {
        timetableDao.deleteSlot(slot)
    }

    suspend fun toggleSlotCompletion(slotId: Long, completed: Boolean) = withContext(Dispatchers.IO) {
        timetableDao.toggleSlotCompletion(slotId, completed)
    }

    // Study Notes
    fun getStudyNotes(userId: Long): Flow<List<StudyNoteEntity>> =
        studyNoteDao.getAllNotes(userId)

    suspend fun insertStudyNote(note: StudyNoteEntity): Long = withContext(Dispatchers.IO) {
        studyNoteDao.insertNote(note)
    }

    suspend fun updateStudyNote(note: StudyNoteEntity) = withContext(Dispatchers.IO) {
        studyNoteDao.updateNote(note)
    }

    suspend fun deleteStudyNote(note: StudyNoteEntity) = withContext(Dispatchers.IO) {
        studyNoteDao.deleteNote(note)
    }

    suspend fun toggleNotePin(noteId: Long, isPinned: Boolean) = withContext(Dispatchers.IO) {
        studyNoteDao.togglePin(noteId, isPinned)
    }

    private fun getCurrentDateStr(): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    }
}
