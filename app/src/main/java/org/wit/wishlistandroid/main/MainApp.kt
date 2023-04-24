package org.wit.wishlistandroid.main

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import org.wit.wishlistandroid.models.DataModel
import org.wit.wishlistandroid.models.NodeModel
import org.wit.wishlistandroid.models.NodeStore
import org.wit.wishlistandroid.models.NodesJSONStore
import timber.log.Timber
import timber.log.Timber.i


class MainApp: Application() {

    //val wishlists = WishlistMemStore()
    lateinit var nodes: NodeStore

    var temperature = 0
    var humidity = 0
    var moisture = 0

    val data = DataModel()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        nodes = NodesJSONStore(applicationContext)
        i("App started")


        val database = FirebaseDatabase.getInstance("https://homegrower-database-default-rtdb.europe-west1.firebasedatabase.app/")
        val myRef = database.getReference("message")

        val test = "000001"
        val jsonRef = database.getReference("$test/")
        val query = jsonRef.orderByKey().limitToLast(1)

        myRef.setValue("Hello, World!")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue<String>()
                Log.d(TAG, "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (childSnapshot in dataSnapshot.children) {
                    val key = childSnapshot.key // The unique ID
                    data.humidity = childSnapshot.child("humidity").getValue(Int::class.java) ?: 0
                    data.moisture = childSnapshot.child("moisture").getValue(Int::class.java) ?: 0
                    data.temperature = childSnapshot.child("temperature").getValue(Int::class.java) ?: 0

                    // Display the values in the log
                    Log.d(TAG, "Key: $key")
                    Log.d(TAG, "humidity: $humidity")
                    Log.d(TAG, "moisture: $moisture")
                    Log.d(TAG, "temperature: $temperature")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        })


    }
}