package com.example

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.local.AppDatabase
import com.example.data.repository.ExamRepository
import com.example.ui.components.BottomNavBar
import com.example.ui.screens.AuthScreen
import com.example.ui.screens.DashboardScreen
import com.example.ui.screens.LeaderboardScreen
import com.example.ui.screens.PracticeSetupScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.QuizResultScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.screens.StudyMaterialScreen
import com.example.ui.screens.TimetableScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.util.NotificationHelper
import com.example.viewmodel.ExamViewModel
import com.example.viewmodel.Screen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Create practice reminder notification channel
        NotificationHelper.createNotificationChannel(applicationContext)

        val database = AppDatabase.getDatabase(applicationContext)
        val repository = ExamRepository(database, applicationContext)

        setContent {
            val examViewModel: ExamViewModel = viewModel { ExamViewModel(repository) }
            val themeMode by examViewModel.themeMode.collectAsState()

            // Request Notification Permission on Android 13+
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                val permissionLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission()
                ) { /* Permission granted or denied handled gracefully */ }

                LaunchedEffect(Unit) {
                    if (ContextCompat.checkSelfPermission(
                            this@MainActivity,
                            Manifest.permission.POST_NOTIFICATIONS
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }
                }
            }

            MyApplicationTheme(themeMode = themeMode) {
                MainAppContent(viewModel = examViewModel)
            }
        }
    }
}

@Composable
fun MainAppContent(viewModel: ExamViewModel) {
    val currentScreen by viewModel.currentScreen.collectAsState()
    val userMessage by viewModel.userMessage.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(userMessage) {
        userMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearUserMessage()
        }
    }

    // Secondary screen back handler logic
    if (currentScreen != Screen.Auth && currentScreen != Screen.Dashboard) {
        BackHandler {
            when (currentScreen) {
                Screen.PracticeSetup,
                Screen.Timetable,
                Screen.StudyMaterial,
                Screen.Leaderboard,
                Screen.Profile -> viewModel.navigateTo(Screen.Dashboard)
                Screen.QuizResult -> viewModel.navigateTo(Screen.Dashboard)
                Screen.Quiz -> {
                    // Handled inside QuizScreen with confirmation dialog
                }
                else -> viewModel.navigateTo(Screen.Dashboard)
            }
        }
    }

    val isMainTabScreen = currentScreen in listOf(
        Screen.Dashboard,
        Screen.PracticeSetup,
        Screen.Timetable,
        Screen.StudyMaterial,
        Screen.Leaderboard,
        Screen.Profile
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            if (isMainTabScreen) {
                BottomNavBar(
                    currentScreen = currentScreen,
                    onNavigate = { viewModel.navigateTo(it) }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedContent(
                targetState = currentScreen,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "screen_transition"
            ) { screen ->
                when (screen) {
                    Screen.Auth -> AuthScreen(viewModel = viewModel)
                    Screen.Dashboard -> DashboardScreen(viewModel = viewModel)
                    Screen.PracticeSetup -> PracticeSetupScreen(viewModel = viewModel)
                    Screen.Quiz -> QuizScreen(viewModel = viewModel)
                    Screen.QuizResult -> QuizResultScreen(viewModel = viewModel)
                    Screen.Timetable -> TimetableScreen(viewModel = viewModel)
                    Screen.StudyMaterial -> StudyMaterialScreen(viewModel = viewModel)
                    Screen.Leaderboard -> LeaderboardScreen(viewModel = viewModel)
                    Screen.Profile -> ProfileScreen(viewModel = viewModel)
                }
            }
        }
    }
}
