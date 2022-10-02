package com.mazer.exhbuy.data.db

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.mazer.exhbuy.data.model.EventData
import com.mazer.exhbuy.data.model.TicketType

object FirestoreDao {

    private val db = FirebaseFirestore.getInstance()

    fun getEvents(
        events: MutableList<EventData>
    ) {
        db.collection("Events").whereEqualTo("approved", true).get()
            .addOnSuccessListener {
                events.clear()
                it.forEach { document ->
                    val id = document.id
                    val event = document.toObject<EventData>()
                    event.firestore_id = id
                    events.add(event)
                    Log.d("findeid", event.firestore_id)
                }
//                events.addAll(it.toObjects(EventData::class.java))
            }
            .addOnFailureListener {
                Log.e("getEventsError", "${it.message}")
                events.clear()
            }
    }

    fun getTicketTypesForEvent(
        event: String,
        tickets: MutableList<TicketType>
    ) {
        db.collection("Events").document(event).collection("Tickets").get()
            .addOnSuccessListener {
                tickets.clear()
                tickets.addAll(it.toObjects(TicketType::class.java))
                Log.d("ticketTypeSize", tickets.size.toString())
            }
            .addOnFailureListener {
                tickets.clear()
                Log.e("getTicketTypesForEvent", it.message!!)
            }
    }
}