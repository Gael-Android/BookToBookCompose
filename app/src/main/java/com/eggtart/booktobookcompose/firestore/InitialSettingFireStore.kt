package com.eggtart.booktobookcompose.firestore

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await

object InitialSettingFireStore {
    private const val TAG = "KWK_InitialSettingFS"
    private val uid = FirebaseAuth.getInstance().currentUser?.uid.toString()

    fun updateDisplayName(displayName: String?) {
        val db = Firebase.firestore
        db.collection("user").document(uid)
            .update("displayName", displayName)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
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

    fun updateBelong(belong: String?) {
        val db = Firebase.firestore
        db.collection("user").document(uid)
            .update("belong", belong)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }

    fun uploadProfileImage(imageUri: Uri?) {
        val storageRef = Firebase.storage.reference

        if (imageUri != null) {
            storageRef.child("user_images/${uid}")
                .putFile(imageUri)
                .addOnFailureListener { Log.d(TAG, "User Image upload fail!") }
                .addOnSuccessListener { Log.d(TAG, "User Image successfully upload!") }
        } else {
            Log.d(TAG, "image Uri is null!!!")
        }
    }
}