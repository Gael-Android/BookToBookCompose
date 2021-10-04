package com.eggtart.booktobookcompose.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.eggtart.booktobookcompose.ui.theme.BookToBookComposeTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi

class TestActivity : ComponentActivity() {
//    @ExperimentalMaterialApi
//    @ExperimentalPagerApi
//    @ExperimentalFoundationApi
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            BookToBookComposeTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    ContentView()
//                }
//            }
//        }
//    }

    @ExperimentalPermissionsApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookToBookComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    BarcodeScanScreen()
                }
            }
        }
    }
}
