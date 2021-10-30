package com.eggtart.booktobookcompose.viewmodel

import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.eggtart.booktobookcompose.firestore.UserFireStore
import com.eggtart.booktobookcompose.navigation.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class InitialSettingViewModel constructor(
    private val navController: NavController
) : ViewModel() {

    private val _displayName = MutableStateFlow("")
    val displayName = _displayName.asStateFlow()

    private val _belong = MutableStateFlow("")
    val belong = _belong.asStateFlow()

    private val _imageUri = MutableStateFlow<Uri?>(null)
    val imageUri = _imageUri.asStateFlow()


    init {
        Log.d(TAG, "InitialSettingViewModel INIT")
        viewModelScope.launch(Dispatchers.IO) {
            UserFireStore.getDisplayName().collect {
                _displayName.value = it
            }
            UserFireStore.getBelong().collect {
                _belong.value = it
            }
        }
    }

    fun onSubmitButtonClick() {
        Log.d(TAG, "onSubmitButtonClick")
        viewModelScope.launch(Dispatchers.IO) {
            UserFireStore.updateDisplayName(_displayName.value)
            UserFireStore.updateBelong(_belong.value)
            UserFireStore.uploadProfileImage(_imageUri.value)
        }
        navController.navigate(Screen.ContentScreen.route)
    }

    fun onProfileImageClick() {
        Log.d(TAG, "onProfileImageClick:${imageUri.value}")
    }

    fun setImageUri(fileUri: Uri?) {
        Log.d(TAG,"setImageUri(${fileUri.toString()})")
        _imageUri.value = fileUri
    }

    fun setDisplayName(displayName: String) {
        _displayName.value = displayName
    }

    fun setBelong(belong: String) {
        _belong.value = belong
    }

    companion object {
        private const val TAG = "KWK_InitialSettingVM"
    }
}
