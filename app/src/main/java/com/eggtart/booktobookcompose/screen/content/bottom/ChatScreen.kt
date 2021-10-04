package com.eggtart.booktobookcompose.screen

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.eggtart.booktobookcompose.data.DataProvider
import com.eggtart.booktobookcompose.data.RandomUser

@Composable
fun ChatScreen() {
    Scaffold(topBar = { AppBar() }) {
        RandomUserListView(randomUsers = DataProvider.userList)
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ProfileImg(imgUrl: String, modifier: Modifier = Modifier) {
    val bitmap: MutableState<Bitmap?> = mutableStateOf(null)
    val imageModifier =
        modifier
            .size(47.dp, 47.dp)
            .clip(RoundedCornerShape(10.dp))

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(imgUrl)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmap.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }

        })

    bitmap.value?.asImageBitmap()?.let { fetchedBitmap ->
        Image(
            bitmap = fetchedBitmap, contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = imageModifier
        )
    }
}

@Composable
fun AppBar() {
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
            Text(text = "채팅", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Icon(Icons.Default.Search, "search")
        }
    }
}

@Composable
fun RandomUserListView(randomUsers: List<RandomUser>) {
    LazyColumn {
        items(randomUsers) {
            //for문을 돌아 randomuser 클래스 하나를 넘겨준다
            RandomUserView(it)
        }
    }
}

@Composable
fun RandomUserView(randomUser: RandomUser) {
    val typography: Typography = MaterialTheme.typography

    Column() {
        Row(
            modifier = Modifier
                .height(74.dp)
                .padding(20.dp, 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileImg(imgUrl = randomUser.ProfileImg)

            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp)
                ) {
                    Text(text = randomUser.name, style = typography.body2)
                    Text(text = randomUser.timestamp, style = typography.body2)
                }

                Text(
                    text = randomUser.recentMsg,
                    style = typography.h1,
                    modifier = Modifier.padding(10.dp, 2.dp)
                )
            }
        }

        Divider(
            color = Color.LightGray, thickness = 0.5.dp,
            modifier = Modifier.padding(20.dp, 0.dp)
        )

    }
}