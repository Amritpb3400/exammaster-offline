package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val AvatarGradients = listOf(
    listOf(Color(0xFF4F46E5), Color(0xFF7C3AED)),
    listOf(Color(0xFF059669), Color(0xFF10B981)),
    listOf(Color(0xFFD97706), Color(0xFFF59E0B)),
    listOf(Color(0xFFDC2626), Color(0xFFF87171)),
    listOf(Color(0xFF0284C7), Color(0xFF38BDF8)),
    listOf(Color(0xFF7C3AED), Color(0xFFA855F7)),
    listOf(Color(0xFFDB2777), Color(0xFFF472B6)),
    listOf(Color(0xFF475569), Color(0xFF64748B))
)

val AvatarIcons = listOf(
    Icons.Default.School,
    Icons.Default.WorkspacePremium,
    Icons.Default.EmojiEvents,
    Icons.Default.Psychology,
    Icons.Default.AutoStories,
    Icons.Default.Lightbulb,
    Icons.Default.Star,
    Icons.Default.Person
)

@Composable
fun AvatarView(
    avatarId: Int,
    name: String,
    size: Dp = 48.dp,
    modifier: Modifier = Modifier
) {
    val gradientColors = AvatarGradients.getOrElse(avatarId % AvatarGradients.size) { AvatarGradients[0] }
    val icon = AvatarIcons.getOrElse(avatarId % AvatarIcons.size) { AvatarIcons[0] }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(Brush.linearGradient(gradientColors))
            .border(2.dp, Color.White.copy(alpha = 0.4f), CircleShape)
    ) {
        if (avatarId == 7 && name.isNotBlank()) {
            val initial = name.trim().take(1).uppercase()
            Text(
                text = initial,
                color = Color.White,
                fontSize = (size.value * 0.45f).sp,
                fontWeight = FontWeight.Bold
            )
        } else {
            Icon(
                imageVector = icon,
                contentDescription = "Avatar",
                tint = Color.White,
                modifier = Modifier.size(size * 0.55f)
            )
        }
    }
}
