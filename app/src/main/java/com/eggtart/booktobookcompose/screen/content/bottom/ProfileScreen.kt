package com.eggtart.booktobookcompose.screen.content.bottom

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen() {
    Scaffold(topBar = { ProfileAppBar() }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Card(
                modifier = Modifier
                    .size(156.dp)
                    .offset(0.dp, 120.dp),
                shape = CircleShape,
                elevation = 16.dp, backgroundColor = Color.LightGray
            ) {
            }
        }
    }
}

@Composable
fun ProfileAppBar() {
    TopAppBar(
        elevation = 10.dp,
        backgroundColor = Color.White,
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Menu, "menu")
            Text(text = "프로필", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Icon(Icons.Default.Create, "create")
        }
    }

}

