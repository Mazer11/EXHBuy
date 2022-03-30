package com.mazer.exhbuy.data

import android.widget.Toast
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects

object FirestoreDao {

    private val db = FirebaseFirestore.getInstance()

    fun getEvents(
        events: SnapshotStateList<EventData>
    ){
        db.collection("Events").get().addOnSuccessListener {
            events.clear()
            events.addAll(it.toObjects(EventData::class.java))
        }
            .addOnFailureListener {
                events.clear()
            }
    }
}