package com.mazer.exhbuy.viewmodels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mazer.exhbuy.data.model.EventData
import com.mazer.exhbuy.data.model.TicketType
import javax.inject.Inject

class CreatingVM @Inject constructor() : ViewModel() {

    private val db = Firebase.firestore
    private val dbCollection = db.collection("Events")

    fun sendEventData(
        eventName: String,
        eventLocation: String,
        startDate: String,
        endDate: String,
        tickets: MutableList<TicketType>
    ) {
        dbCollection.add(
            EventData(
                event_name = eventName,
                event_location = eventLocation,
                date_of_start = startDate,
                date_of_end = endDate,
            )
        )
            .addOnSuccessListener { documentReference ->
                Log.d(
                    ContentValues.TAG,
                    "DocumentSnapshot added with ID: ${documentReference.id}"
                )
                tickets.forEachIndexed { index, ticketType ->
                    documentReference.collection("Tickets")
                        .document("${index + 1}")
                        .set(ticketType)
                        .addOnSuccessListener {
                            Log.d(
                                ContentValues.TAG,
                                "Document ticketType added"
                            )
                        }
                }
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            }
    }
}