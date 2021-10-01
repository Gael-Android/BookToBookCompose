package com.eggtart.booktobookcompose.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eggtart.booktobookcompose.firestore.InitialSettingFireStore
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InitialSettingViewModel : ViewModel() {
    val displayName = MutableLiveData("")
    val belong = MutableLiveData("")
    val imageUri = MutableLiveData<Uri?>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            displayName.postValue(InitialSettingFireStore.getDisplayName())
        }
    }

    fun onSubmitButtonClick() {
        Log.d(TAG, "onSubmitButtonClick")
        InitialSettingFireStore.updateDisplayName(displayName.value)
        InitialSettingFireStore.updateBelong(belong.value)
        InitialSettingFireStore.uploadProfileImage(imageUri.value)
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