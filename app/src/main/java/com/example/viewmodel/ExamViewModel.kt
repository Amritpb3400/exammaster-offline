package com.example.viewmodel

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.InitialData
import com.example.data.local.entity.QuestionEntity
import com.example.data.local.entity.StudyNoteEntity
import com.example.data.local.entity.TestAttemptEntity
import com.example.data.local.entity.TimetableSlotEntity
import com.example.data.local.entity.UserEntity
import com.example.data.repository.ExamRepository
import com.example.ui.theme.ThemeMode
import com.example.util.NotificationHelper
import com.example.util.PdfHelper
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

sealed class Screen {
    object Auth : Screen()
    object Dashboard : Screen()
    object PracticeSetup : Screen()
    object Quiz : Screen()
    object QuizResult : Screen()
    object Timetable : Screen()
    object StudyMaterial : Screen()
    object Leaderboard : Screen()
    object Profile : Screen()
}

data class QuizState(
    val questions: List<QuestionEntity> = emptyList(),
    val currentIndex: Int = 0,
    val userAnswers: Map<Int, Int> = emptyMap(), // questionIndex -> selectedOption (0..3)
    val flaggedForReview: Set<Int> = emptySet(),
    val remainingSeconds: Int = 0,
    val totalTimeSeconds: Int = 0,
    val isFinished: Boolean = false,
    val isTimerActive: Boolean = false,
    val examCategory: String = "All",
    val subject: String = "All"
)

data class LeaderboardEntry(
    val rank: Int,
    val name: String,
    val targetExam: String,
    val xp: Int,
    val streak: Int,
    val accuracy: Int,
    val avatarId: Int,
    val isCurrentUser: Boolean = false
)

class ExamViewModel(private val repository: ExamRepository) : ViewModel() {

    private val _themeMode = MutableStateFlow(ThemeMode.SYSTEM)
    val themeMode: StateFlow<ThemeMode> = _themeMode.asStateFlow()

    private val _currentScreen = MutableStateFlow<Screen>(Screen.Auth)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    private val _currentUserId = MutableStateFlow<Long?>(null)

    val currentUser: StateFlow<UserEntity?> = _currentUserId.flatMapLatest { id ->
        if (id == null) flowOf(null) else repository.observeUser(id)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val timetableSlots: StateFlow<List<TimetableSlotEntity>> = _currentUserId.flatMapLatest { id ->
        if (id == null) flowOf(emptyList()) else repository.getTimetableSlots(id)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val studyNotes: StateFlow<List<StudyNoteEntity>> = _currentUserId.flatMapLatest { id ->
        if (id == null) flowOf(emptyList()) else repository.getStudyNotes(id)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val recentAttempts: StateFlow<List<TestAttemptEntity>> = _currentUserId.flatMapLatest { id ->
        if (id == null) flowOf(emptyList()) else repository.getRecentAttempts(id)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val totalAttemptsCount: StateFlow<Int> = _currentUserId.flatMapLatest { id ->
        if (id == null) flowOf(0) else repository.getTotalAttemptsCount(id)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val averageAccuracy: StateFlow<Float?> = _currentUserId.flatMapLatest { id ->
        if (id == null) flowOf(null) else repository.getAverageAccuracy(id)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    // Quiz Session State
    private val _quizState = MutableStateFlow(QuizState())
    val quizState: StateFlow<QuizState> = _quizState.asStateFlow()

    private val _lastAttemptResult = MutableStateFlow<TestAttemptEntity?>(null)
    val lastAttemptResult: StateFlow<TestAttemptEntity?> = _lastAttemptResult.asStateFlow()

    private var timerJob: Job? = null

    // PDF & Study Guides State
    private val _loadedPdf = MutableStateFlow<PdfHelper.LoadedPdf?>(null)
    val loadedPdf: StateFlow<PdfHelper.LoadedPdf?> = _loadedPdf.asStateFlow()

    private val _pdfPageBitmap = MutableStateFlow<Bitmap?>(null)
    val pdfPageBitmap: StateFlow<Bitmap?> = _pdfPageBitmap.asStateFlow()

    private val _currentPdfPageIndex = MutableStateFlow(0)
    val currentPdfPageIndex: StateFlow<Int> = _currentPdfPageIndex.asStateFlow()

    private val _selectedPreloadedGuide = MutableStateFlow<InitialData.PreloadedStudyGuide?>(InitialData.preloadedGuides.firstOrNull())
    val selectedPreloadedGuide: StateFlow<InitialData.PreloadedStudyGuide?> = _selectedPreloadedGuide.asStateFlow()

    private val _currentGuidePageIndex = MutableStateFlow(0)
    val currentGuidePageIndex: StateFlow<Int> = _currentGuidePageIndex.asStateFlow()

    // Status snackbar/message state
    private val _userMessage = MutableStateFlow<String?>(null)
    val userMessage: StateFlow<String?> = _userMessage.asStateFlow()

    init {
        viewModelScope.launch {
            repository.initializeDatabaseIfNeeded()
            // Resume the account that was last signed in (not just user id 1),
            // so switching between registered accounts survives an app restart.
            val lastUserId = repository.getLastLoggedInUserId()
            val user = lastUserId?.let { repository.getUserSync(it) }
            if (user != null) {
                _currentUserId.value = user.id
                _currentScreen.value = Screen.Dashboard
                repository.recordActivityAndStreak(user.id)
            }
        }
    }

    fun setThemeMode(mode: ThemeMode) {
        _themeMode.value = mode
    }

    fun navigateTo(screen: Screen) {
        _currentScreen.value = screen
    }

    fun clearUserMessage() {
        _userMessage.value = null
    }

    fun showMessage(msg: String) {
        _userMessage.value = msg
    }

    // Auth actions
    fun login(username: String, pass: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        if (username.isBlank() || pass.isBlank()) {
            onError("Please enter both username and password")
            return
        }
        viewModelScope.launch {
            val result = repository.login(username, pass)
            result.onSuccess { user ->
                _currentUserId.value = user.id
                repository.recordActivityAndStreak(user.id)
                _currentScreen.value = Screen.Dashboard
                onSuccess()
            }.onFailure { err ->
                onError(err.message ?: "Login failed")
            }
        }
    }

    fun register(
        username: String,
        pass: String,
        displayName: String,
        targetExam: String,
        targetYear: String,
        avatarId: Int,
        bio: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (username.isBlank() || pass.isBlank()) {
            onError("Username and password are required")
            return
        }
        viewModelScope.launch {
            val result = repository.register(
                username = username,
                password = pass,
                displayName = displayName,
                targetExam = targetExam,
                targetYear = targetYear,
                avatarId = avatarId,
                bio = bio
            )
            result.onSuccess { user ->
                _currentUserId.value = user.id
                _currentScreen.value = Screen.Dashboard
                onSuccess()
            }.onFailure { err ->
                onError(err.message ?: "Registration failed")
            }
        }
    }

    fun logout() {
        repository.clearSession()
        _currentUserId.value = null
        _currentScreen.value = Screen.Auth
    }

    fun updateProfile(displayName: String, targetExam: String, targetYear: String, avatarId: Int, bio: String) {
        val user = currentUser.value ?: return
        viewModelScope.launch {
            val updated = user.copy(
                displayName = displayName.ifBlank { user.displayName },
                targetExam = targetExam,
                targetYear = targetYear,
                avatarId = avatarId,
                bio = bio
            )
            repository.updateUser(updated)
            showMessage("Profile updated successfully!")
        }
    }

    // Quiz Execution
    fun startCustomQuiz(
        examCategory: String,
        subject: String,
        difficulty: String,
        questionCount: Int,
        timeLimitMinutes: Int
    ) {
        viewModelScope.launch {
            val questions = repository.getQuestionsForQuiz(
                category = examCategory,
                subject = subject,
                difficulty = difficulty,
                limit = questionCount
            )

            if (questions.isEmpty()) {
                showMessage("No questions found for selected criteria. Try 'All'!")
                return@launch
            }

            val totalSec = if (timeLimitMinutes <= 0) 0 else timeLimitMinutes * 60

            _quizState.value = QuizState(
                questions = questions,
                currentIndex = 0,
                userAnswers = emptyMap(),
                flaggedForReview = emptySet(),
                remainingSeconds = totalSec,
                totalTimeSeconds = totalSec,
                isFinished = false,
                isTimerActive = totalSec > 0,
                examCategory = examCategory,
                subject = subject
            )

            startTimer(totalSec)
            _currentScreen.value = Screen.Quiz
        }
    }

    private fun startTimer(totalSeconds: Int) {
        timerJob?.cancel()
        if (totalSeconds <= 0) return

        timerJob = viewModelScope.launch {
            while (_quizState.value.remainingSeconds > 0 && !_quizState.value.isFinished) {
                delay(1000)
                _quizState.value = _quizState.value.copy(
                    remainingSeconds = _quizState.value.remainingSeconds - 1
                )
            }
            if (_quizState.value.remainingSeconds <= 0 && !_quizState.value.isFinished) {
                submitQuiz()
            }
        }
    }

    fun selectQuizAnswer(optionIndex: Int) {
        val current = _quizState.value
        val updated = current.userAnswers.toMutableMap()
        updated[current.currentIndex] = optionIndex
        _quizState.value = current.copy(userAnswers = updated)
    }

    fun toggleFlagForReview() {
        val current = _quizState.value
        val flagged = current.flaggedForReview.toMutableSet()
        if (flagged.contains(current.currentIndex)) {
            flagged.remove(current.currentIndex)
        } else {
            flagged.add(current.currentIndex)
        }
        _quizState.value = current.copy(flaggedForReview = flagged)
    }

    fun nextQuestion() {
        val current = _quizState.value
        if (current.currentIndex < current.questions.size - 1) {
            _quizState.value = current.copy(currentIndex = current.currentIndex + 1)
        }
    }

    fun prevQuestion() {
        val current = _quizState.value
        if (current.currentIndex > 0) {
            _quizState.value = current.copy(currentIndex = current.currentIndex - 1)
        }
    }

    fun jumpToQuestion(index: Int) {
        val current = _quizState.value
        if (index in current.questions.indices) {
            _quizState.value = current.copy(currentIndex = index)
        }
    }

    fun submitQuiz() {
        timerJob?.cancel()
        val current = _quizState.value
        val questions = current.questions
        var correctCount = 0
        var incorrectCount = 0
        var unattemptedCount = 0

        questions.forEachIndexed { index, question ->
            val userAns = current.userAnswers[index]
            if (userAns == null) {
                unattemptedCount++
            } else if (userAns == question.correctOption) {
                correctCount++
            } else {
                incorrectCount++
            }
        }

        val totalQ = questions.size
        val accuracy = if (totalQ > 0) (correctCount.toFloat() / totalQ.toFloat()) * 100f else 0f
        // Use Double math and round at the end so partial (0.5) negative marking
        // per wrong answer isn't silently truncated away (e.g. 1 or 3 wrong answers
        // used to lose 0 / 1 marks instead of the intended 0.5 / 1.5).
        val rawScore = (correctCount * 2.0) - (incorrectCount * 0.5)
        val finalScore = rawScore.roundToInt().coerceAtLeast(0)
        val xpEarned = (correctCount * 15) + (if (accuracy >= 80f) 50 else 20)

        val timeTaken = if (current.totalTimeSeconds > 0) {
            (current.totalTimeSeconds - current.remainingSeconds).coerceAtLeast(1)
        } else {
            60
        }

        val dateFormatted = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault()).format(Date())

        val attempt = TestAttemptEntity(
            userId = _currentUserId.value ?: 0L,
            examCategory = current.examCategory,
            subjectFilter = current.subject,
            totalQuestions = totalQ,
            correctCount = correctCount,
            incorrectCount = incorrectCount,
            unattemptedCount = unattemptedCount,
            accuracy = accuracy,
            score = finalScore,
            xpEarned = xpEarned,
            timeTakenSeconds = timeTaken,
            totalTimeSeconds = current.totalTimeSeconds,
            dateFormatted = dateFormatted
        )

        viewModelScope.launch {
            val user = currentUser.value
            if (user != null) {
                repository.saveTestAttempt(attempt)
                repository.addXp(user.id, xpEarned)
                repository.recordActivityAndStreak(user.id)
            }
            _lastAttemptResult.value = attempt
            _quizState.value = current.copy(isFinished = true)
            _currentScreen.value = Screen.QuizResult
        }
    }

    // Timetable Actions
    fun addTimetableSlot(dayOfWeek: String, startTime: String, endTime: String, subject: String, notes: String) {
        val uid = _currentUserId.value ?: return
        viewModelScope.launch {
            val slot = TimetableSlotEntity(
                userId = uid,
                dayOfWeek = dayOfWeek,
                startTime = startTime,
                endTime = endTime,
                subject = subject,
                topicNotes = notes
            )
            repository.insertSlot(slot)
            showMessage("Study slot added!")
        }
    }

    fun updateTimetableSlot(slot: TimetableSlotEntity) {
        viewModelScope.launch {
            repository.updateSlot(slot)
            showMessage("Slot updated!")
        }
    }

    fun deleteTimetableSlot(slot: TimetableSlotEntity) {
        viewModelScope.launch {
            repository.deleteSlot(slot)
            showMessage("Slot removed")
        }
    }

    fun toggleSlotCompletion(slot: TimetableSlotEntity) {
        viewModelScope.launch {
            repository.toggleSlotCompletion(slot.id, !slot.isCompletedToday)
        }
    }

    // Notes Actions
    fun addStudyNote(title: String, subject: String, content: String, sourcePdfName: String? = null) {
        val uid = _currentUserId.value ?: return
        if (title.isBlank()) {
            showMessage("Please provide a note title")
            return
        }
        viewModelScope.launch {
            val note = StudyNoteEntity(
                userId = uid,
                title = title.trim(),
                subject = subject.ifBlank { "General" },
                content = content.trim(),
                sourcePdfName = sourcePdfName
            )
            repository.insertStudyNote(note)
            showMessage("Note saved!")
        }
    }

    fun updateStudyNote(note: StudyNoteEntity) {
        viewModelScope.launch {
            repository.updateStudyNote(note)
            showMessage("Note updated!")
        }
    }

    fun deleteStudyNote(note: StudyNoteEntity) {
        viewModelScope.launch {
            repository.deleteStudyNote(note)
            showMessage("Note deleted")
        }
    }

    fun toggleNotePin(note: StudyNoteEntity) {
        viewModelScope.launch {
            repository.toggleNotePin(note.id, !note.isPinned)
        }
    }

    // PDF Actions
    fun loadPdfFromUri(context: Context, uri: Uri) {
        viewModelScope.launch {
            showMessage("Loading PDF...")
            val loaded = PdfHelper.savePdfToCache(context, uri)
            if (loaded != null) {
                _loadedPdf.value = loaded
                _currentPdfPageIndex.value = 0
                renderCurrentPdfPage(loaded.localFilePath, 0)
                showMessage("PDF loaded successfully: ${loaded.fileName}")
            } else {
                showMessage("Could not open PDF. Please ensure file is valid.")
            }
        }
    }

    private fun renderCurrentPdfPage(path: String, index: Int) {
        viewModelScope.launch {
            val bmp = PdfHelper.renderPageToBitmap(path, index)
            _pdfPageBitmap.value = bmp
        }
    }

    fun nextPdfPage() {
        val loaded = _loadedPdf.value ?: return
        val currentIdx = _currentPdfPageIndex.value
        if (currentIdx < loaded.pageCount - 1) {
            val nextIdx = currentIdx + 1
            _currentPdfPageIndex.value = nextIdx
            renderCurrentPdfPage(loaded.localFilePath, nextIdx)
        }
    }

    fun prevPdfPage() {
        val loaded = _loadedPdf.value ?: return
        val currentIdx = _currentPdfPageIndex.value
        if (currentIdx > 0) {
            val prevIdx = currentIdx - 1
            _currentPdfPageIndex.value = prevIdx
            renderCurrentPdfPage(loaded.localFilePath, prevIdx)
        }
    }

    fun selectPreloadedGuide(guide: InitialData.PreloadedStudyGuide) {
        _selectedPreloadedGuide.value = guide
        _currentGuidePageIndex.value = 0
    }

    fun nextGuidePage() {
        val guide = _selectedPreloadedGuide.value ?: return
        if (_currentGuidePageIndex.value < guide.pages.size - 1) {
            _currentGuidePageIndex.value += 1
        }
    }

    fun prevGuidePage() {
        if (_currentGuidePageIndex.value > 0) {
            _currentGuidePageIndex.value -= 1
        }
    }

    // Questions Sync
    fun syncOnlineQuestions() {
        viewModelScope.launch {
            val added = repository.syncNewQuestions()
            showMessage("Synced! $added new high-yield questions added to local database.")
        }
    }

    fun addCustomQuestion(
        category: String,
        subject: String,
        topic: String,
        difficulty: String,
        text: String,
        optA: String,
        optB: String,
        optC: String,
        optD: String,
        correctOpt: Int,
        explanation: String
    ) {
        viewModelScope.launch {
            val q = QuestionEntity(
                examCategory = category,
                subject = subject,
                topic = topic,
                difficulty = difficulty,
                questionText = text,
                optionA = optA,
                optionB = optB,
                optionC = optC,
                optionD = optD,
                correctOption = correctOpt,
                explanation = explanation,
                isCustom = true
            )
            repository.addCustomQuestion(q)
            showMessage("Custom question added to practice pool!")
        }
    }

    // Reminders & Notifications
    fun sendPracticeNotification(context: Context) {
        val success = NotificationHelper.sendPracticeReminder(
            context = context,
            title = "⏰ Practice Reminder: Time to Study!",
            message = "Stay on track for your ${currentUser.value?.targetExam ?: "Competitive Exam"}! Complete your 15-min mock test to protect your ${currentUser.value?.streak ?: 1}-day streak! 🔥"
        )
        if (success) {
            showMessage("Reminder notification sent!")
        } else {
            showMessage("Notification sent (please grant notification permission if prompted)")
        }
    }

    // Share Progress
    fun shareProgress(context: Context) {
        val user = currentUser.value ?: return
        val shareText = """
            🎯 ExamPrep Pro - My Progress Report 🎯
            
            👤 Candidate: ${user.displayName}
            📚 Target Exam: ${user.targetExam} (${user.targetYear})
            🔥 Daily Streak: ${user.streak} Days
            ⚡ Total XP: ${user.totalXp} pts
            🏆 Tests Taken: ${totalAttemptsCount.value}
            📊 Avg Accuracy: ${String.format(Locale.getDefault(), "%.1f", averageAccuracy.value ?: 0f)}%
            
            Practicing daily with offline mock tests & notes on ExamPrep Pro!
        """.trimIndent()

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, "Share Exam Progress")
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(shareIntent)
    }

    // Leaderboard calculation
    fun getLeaderboard(): List<LeaderboardEntry> {
        val user = currentUser.value
        val peers = InitialData.peerCompetitors.map { peer ->
            LeaderboardEntry(
                rank = 0,
                name = peer.name,
                targetExam = peer.targetExam,
                xp = peer.xp,
                streak = peer.streak,
                accuracy = peer.accuracy,
                avatarId = peer.avatarId,
                isCurrentUser = false
            )
        }

        val userEntry = if (user != null) {
            LeaderboardEntry(
                rank = 0,
                name = user.displayName,
                targetExam = user.targetExam,
                xp = user.totalXp,
                streak = user.streak,
                accuracy = (averageAccuracy.value ?: 85f).toInt(),
                avatarId = user.avatarId,
                isCurrentUser = true
            )
        } else null

        val combined = if (userEntry != null) (peers + userEntry) else peers
        return combined.sortedByDescending { it.xp }.mapIndexed { index, entry ->
            entry.copy(rank = index + 1)
        }
    }
}
