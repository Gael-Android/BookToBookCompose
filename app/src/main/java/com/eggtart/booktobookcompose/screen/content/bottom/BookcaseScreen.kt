package com.eggtart.booktobookcompose.screen.content.bottom

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.eggtart.booktobookcompose.navigation.BottomNavigation

@Composable
fun BookcaseScreen() {
    Text(
        text = BottomNavigation.Bookcase.title,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.primary,
        fontSize = 20.sp
    )
}