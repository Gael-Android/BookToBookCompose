package com.eggtart.booktobookcompose.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.eggtart.booktobookcompose.firestore.UserFireStore
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
            displayName.postValue(UserFireStore.getDisplayName())
            belong.postValue(UserFireStore.getBelong())
        }
    }

    fun onSubmitButtonClick() {
        Log.d(TAG, "onSubmitButtonClick")
        viewModelScope.launch(Dispatchers.IO) {
            UserFireStore.updateDisplayName(displayName.value)
            UserFireStore.updateBelong(belong.value)
            UserFireStore.uploadProfileImage(imageUri.value)
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