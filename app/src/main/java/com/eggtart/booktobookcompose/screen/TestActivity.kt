package com.eggtart.booktobookcompose.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.eggtart.booktobookcompose.screen.barcode.BarcodeScanScreen
import com.eggtart.booktobookcompose.ui.theme.BookToBookComposeTheme
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
//                    ContentScreen()
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
