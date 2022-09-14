package com.mazer.exhbuy.data.db

import com.google.firebase.firestore.FirebaseFirestore
import com.mazer.exhbuy.data.model.EventData

object FirestoreDao {

    private val db = FirebaseFirestore.getInstance()

    fun getEvents(
        events: MutableList<EventData>
    ) {
        db.collection("Events").get()
            .addOnSuccessListener {
                events.clear()
                events.addAll(it.toObjects(EventData::class.java))
            }
            .addOnFailureListener {
                events.clear()
            }
    }
}