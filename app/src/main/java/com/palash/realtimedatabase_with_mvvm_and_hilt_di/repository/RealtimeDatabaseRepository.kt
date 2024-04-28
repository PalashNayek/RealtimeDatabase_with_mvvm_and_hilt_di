package com.palash.realtimedatabase_with_mvvm_and_hilt_di.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject
import javax.inject.Singleton

class RealtimeDatabaseRepository @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase
) {
    fun setData(path: String, value: Any) {
        val ref = firebaseDatabase.getReference(path)
        ref.setValue(value)
    }

    fun observeData(path: String, onDataChange: (DataSnapshot) -> Unit) {
        val ref = firebaseDatabase.getReference(path)
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                onDataChange(snapshot)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
}