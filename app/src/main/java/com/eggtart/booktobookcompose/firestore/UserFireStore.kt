package com.eggtart.booktobookcompose.firestore

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await

object UserFireStore {
    private const val TAG = "KWK_InitialSettingFS"
    private val uid = FirebaseAuth.getInstance().currentUser?.uid.toString()

    suspend fun updateDisplayName(displayName: String?) {
        val db = Firebase.firestore
        try {
            db.collection("user").document(uid)
                .update("displayName", displayName)
                .await()
        } catch (exception: Exception) {
            Log.d(TAG, "Error updating document", exception)
        }
    }

    suspend fun getDisplayName(): String? {
        val db = Firebase.firestore
        return try {
            db.collection("user").document(uid)
                .get()
                .await()
                .data?.get("displayName").toString()
        } catch (exception: Exception) {
            Log.d("KWK", "get failed with ", exception)
            ""
        }
    }

    suspend fun getBelong(): String? {
        val db = Firebase.firestore
        return try {
            db.collection("user").document(uid)
                .get()
                .await()
                .data?.get("belong").toString()
        } catch (exception: Exception) {
            Log.d("KWK", "get failed with ", exception)
            ""
        }
    }

    suspend fun updateBelong(belong: String?) {
        val db = Firebase.firestore
        try {
            db.collection("user").document(uid)
                .update("belong", belong)
                .await()
        } catch (exception: Exception) {
            Log.d(TAG, "Error updating document", exception)
        }
    }

    suspend fun uploadProfileImage(imageUri: Uri?) {
        val storageRef = Firebase.storage.reference

        if (imageUri != null) {
            try {
                storageRef.child("user_images/${uid}")
                    .putFile(imageUri)
                    .await()
            } catch (exception: Exception) {
                Log.d(TAG, "User Image upload fail!")
            }
        } else {
            Log.d(TAG, "image Uri is null!!!")
        }
    }

    @Deprecated("app design not ready for this feature")
    suspend fun downloadProfileImage(): ByteArray? {
        val user = FirebaseAuth.getInstance().currentUser
        val storageReference = Firebase.storage.reference
        val pathReference = storageReference.child("user_images/${user?.uid}")
        val ONE_MEGABYTE: Long = 1024 * 1024
        return try {
            pathReference
                .getBytes(ONE_MEGABYTE)
                .await()
        } catch (exception: Exception) {
            Log.d(TAG, "User Image download fail!")
            null
        }
    }
}