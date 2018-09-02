package com.example.psangule.ultrabakchodi

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.database.*

class ParkActivity : Activity() {

    //var mHostReference : DatabaseReference? = null
    lateinit var mListView : ListView
    var mAddress : ArrayList<String> = ArrayList<String>()
    lateinit var arrayAdapter : ArrayAdapter<String>
    val tempString : String = "Hello World"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_park)
        val mHostReference = FirebaseDatabase.getInstance().reference
        mListView = findViewById<ListView>(R.id.listID)
        arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, mAddress)

        mListView.setAdapter(arrayAdapter)

        mHostReference.addChildEventListener(
                object : ChildEventListener {
                    override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                        val newString : String? = dataSnapshot.getValue(String::class.java)
                        mAddress.add(newString.toString())
                    }

                    override fun onCancelled(p0: DatabaseError?) {

                    }

                    override fun onChildMoved(p0: DataSnapshot?, p1: String?) {

                    }

                    override fun onChildRemoved(p0: DataSnapshot?) {

                    }

                    override fun onChildChanged(p0: DataSnapshot?, p1: String?) {

                    }
                }
        )
    }

    fun displayPlace(view: View){
        arrayAdapter.notifyDataSetChanged()
    }

}