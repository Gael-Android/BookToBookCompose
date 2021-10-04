package com.eggtart.booktobookcompose.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.eggtart.booktobookcompose.firestore.InitialSettingFireStore
import com.eggtart.booktobookcompose.navigation.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InitialSettingViewModel constructor(
    private val navController: NavController
) : ViewModel() {
    val displayName = MutableLiveData("")
    val belong = MutableLiveData("")
    val imageUri = MutableLiveData<Uri?>()

    init {
        Log.d(TAG, "InitialSettingViewModel INIT")
        viewModelScope.launch(Dispatchers.IO) {
            displayName.postValue(InitialSettingFireStore.getDisplayName())
            belong.postValue(InitialSettingFireStore.getBelong())
        }
    }

    fun onSubmitButtonClick() {
        Log.d(TAG, "onSubmitButtonClick")
        viewModelScope.launch(Dispatchers.IO) {
            InitialSettingFireStore.updateDisplayName(displayName.value)
            InitialSettingFireStore.updateBelong(belong.value)
            InitialSettingFireStore.uploadProfileImage(imageUri.value)
        }
        navController.navigate(Screen.ContentScreen.route)
    }

    fun onProfileImageClick() {
        Log.d(TAG, "onProfileImageClick:${imageUri.value}")
    }

    fun onDisplayNameChanged(displayName: String) {
        this.displayName.value = displayName
        Log.d(TAG, "${this.displayName.value}")
    }

    fun onBelongChanged(belong: String) {
        this.belong.value = belong
        Log.d(TAG, "${this.belong.value}")
    }

    fun setImageUri(imageUri: Uri?) {
        this.imageUri.value = imageUri
    }

    companion object {
        private const val TAG = "KWK_InitialSettingVM"
    }
}