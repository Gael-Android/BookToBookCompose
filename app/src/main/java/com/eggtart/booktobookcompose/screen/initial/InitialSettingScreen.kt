package com.eggtart.booktobookcompose.screen.initial

import android.app.Activity
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eggtart.booktobookcompose.R
import com.eggtart.booktobookcompose.ui.theme.BookToBookComposeTheme
import com.eggtart.booktobookcompose.viewmodel.InitialSettingViewModel
import com.github.dhaval2404.imagepicker.ImagePicker
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun InitialSettingScreen(viewModel: InitialSettingViewModel) {
    val context = LocalContext.current
    val displayName: String by viewModel.displayName.observeAsState("")
    val belong: String by viewModel.belong.observeAsState("")
    val imageUri: Uri? by viewModel.imageUri.observeAsState(null)
    Log.d("KWK", "InitialSettingScreen")

    val startForProfileImageResult =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data
            when (resultCode) {
                Activity.RESULT_OK -> {
                    val fileUri = data?.data!!
                    viewModel.setImageUri(fileUri)
                }
                ImagePicker.RESULT_ERROR -> {
                    Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(context, "Task Canceled", Toast.LENGTH_SHORT).show()
                }
            }
        }

    val imageLaunch = {
        ImagePicker.with(context as Activity)
            .compress(1024)
            .crop(1f, 1f)
            .createIntent { intent ->
                startForProfileImageResult.launch(intent)
            }
    }

    InitialSetting(
        displayName =
        if (displayName == "null") {
            ""
        } else {
            displayName
        },
        belong =
        if (belong == "null") {
            ""
        } else {
            belong
        },
        imageUri = imageUri,
        onSubmitButtonClick = { viewModel.onSubmitButtonClick() },
        onProfileImageClick = {
            imageLaunch()
            viewModel.onProfileImageClick()
        },
        onDisplayNameChanged = { viewModel.onDisplayNameChanged(it) },
        onBelongChanged = { viewModel.onBelongChanged(it) }
    )
}

@Composable
fun InitialSetting(
    displayName: String,
    belong: String,
    imageUri: Uri?,
    onSubmitButtonClick: () -> Unit,
    onProfileImageClick: () -> Unit,
    onDisplayNameChanged: (String) -> Unit,
    onBelongChanged: (String) -> Unit,
) {
    Log.d("KWK", "InitialSetting")
    BookToBookComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {

                ProfileImage(imageUri) {
                    onProfileImageClick()
                }

                Spacer(Modifier.size(100.dp))

                DisplayNameInput(displayName) {
                    onDisplayNameChanged(it)
                }

                Spacer(Modifier.size(10.dp))

                BelongInput(belong) {
                    onBelongChanged(it)
                }

                Spacer(Modifier.size(50.dp))

                SubmitButton {
                    onSubmitButtonClick()
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewInitialSetting() {
    InitialSetting(
        displayName = "임의의 이름",
        belong = "임의의 장소",
        imageUri = null,
        onSubmitButtonClick = { },
        onProfileImageClick = { },
        onDisplayNameChanged = { },
        onBelongChanged = { }
    )
}

@Composable
fun SubmitButton(onClick: () -> Unit) {
    OutlinedButton(onClick = onClick) {
        Text("SUBMIT")
    }
}

@Composable
fun DisplayNameInput(displayName: String, onDisplayNameChanged: (String) -> Unit) {
    TextField(
        value = displayName,
        onValueChange = onDisplayNameChanged,
        label = { Text("displayName") }
    )
}

@Composable
fun BelongInput(belong: String, onBelongChanged: (String) -> Unit) {
    TextField(
        value = belong,
        onValueChange = onBelongChanged,
        label = { Text("belong") }
    )
}

@Composable
fun ProfileImage(imageUri: Uri?, onProfileImageClick: () -> Unit) {
    if (imageUri != null) {
        Box {
            GlideImage(
                imageModel = imageUri,
                modifier = Modifier
                    .clickable(onClick = onProfileImageClick)
                    .size(200.dp)
                    .clip(CircleShape),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
            )
            Icon(
                Icons.Rounded.Person, contentDescription = "can modify",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(30.dp, 30.dp)
            )
        }
    } else {
        Box {
            Image(
                painter = painterResource(id = R.drawable.doge),
                contentDescription = "ProfileImage",
                modifier = Modifier
                    .clickable(onClick = onProfileImageClick)
                    .size(200.dp)
                    .clip(CircleShape),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
            )
            Icon(
                Icons.Rounded.Person, contentDescription = "can modify",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(30.dp, 30.dp)
            )
        }
    }
}


