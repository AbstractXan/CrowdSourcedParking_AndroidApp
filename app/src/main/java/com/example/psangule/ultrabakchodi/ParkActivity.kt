package com.example.psangule.ultrabakchodi

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.QuickContactBadge
import com.google.firebase.database.*
import java.net.Inet4Address

class ParkActivity : Activity() {

    data class Host(
            val name: String = "",
            val contact : String = "",
            val city: String = "",
            val address: String = ""
    )

    private var mDatabase : DatabaseReference? = null
    private lateinit var parkCity : EditText
    private val dataDump: MutableList<Host> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_park)
        parkCity = findViewById(R.id.city_field)
    }

    fun parkSearch(view: View){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Host")
        mDatabase?.orderByChild("City")?.equalTo(parkCity.text.toString())?.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.mapNotNullTo(dataDump){
                    it.getValue<Host>(Host::class.java)
                }
              }

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadPost:onCancelled$(databaseError.toException()}")
            }
        })
    }

}
