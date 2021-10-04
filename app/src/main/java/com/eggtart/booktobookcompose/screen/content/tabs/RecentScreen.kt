package com.eggtart.booktobookcompose.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalFoundationApi
@Composable
fun RecentScreen() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        GridItem()
    }
}

@ExperimentalFoundationApi
@Composable
fun GridItem() {
    val list = (1..20).map { }

    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        content = {
            items(list.size) { index: Int ->

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Card(
                        modifier = Modifier.size(72.dp, 91.dp),
                        backgroundColor = Color.Gray, elevation = 8.dp
                    ) {

                    }
                    Text(
                        text = "오징어게임",
                        fontSize = 14.sp,
                        fontStyle = FontStyle.Normal,
                        color = Color.Black
                    )
                    Text(text = "곽건", fontSize = 12.sp, color = Color.LightGray)

                }

            }
        },
        contentPadding = PaddingValues(start = 0.dp, top = 0.dp),
    )
}