package com.eggtart.booktobookcompose.screen.content.bottom


import android.Manifest
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.ViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.CompoundBarcodeView

@ExperimentalPermissionsApi
@Composable
fun EnrollScreen() {
    var scanFlag by remember {
        mutableStateOf(false)
    }

    val cameraPermissionState = rememberPermissionState(
        Manifest.permission.CAMERA
    )

    when {
        cameraPermissionState.hasPermission -> {
            Log.d("KWK_PERMISSION", "permission granted")

            val context = LocalContext.current
            val compoundBarcodeView = remember {
                CompoundBarcodeView(context).apply {
                    val capture = CaptureManager(context as Activity, this)
                    capture.initializeFromIntent(context.intent, null)
                    setStatusText("")
                    this.resume()
                    capture.decode()
                    decodeContinuous { result ->
                        if (scanFlag) {
                            return@decodeContinuous
                        }
                        scanFlag = true
                        result.text?.let { barCodeOrQr ->
                            // DO SOMETHING
                            Log.d("KWK_BARCODE", barCodeOrQr)
                        }
                    }
                }
            }

            AndroidView(modifier = Modifier.fillMaxSize(), factory = { compoundBarcodeView })
        }
        cameraPermissionState.shouldShowRationale || !cameraPermissionState.permissionRequested -> {
            SideEffect {
                cameraPermissionState.launchPermissionRequest()
            }
        }
        else -> {
            Log.d("KWK_PERMISSION", "Camera permission denied.")
        }
    }

}