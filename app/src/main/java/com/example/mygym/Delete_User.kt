package com.example.mygym

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.util.*

class Delete_User : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference
    lateinit var recyal : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete__user)

        var listofuser = ArrayList<Userdata>()
        recyal = findViewById(R.id.recyclerviewDeleteUser)
        databaseReference= FirebaseDatabase.getInstance().getReference("GymUsers")
        val SharedPreferences : SharedPreferences = getSharedPreferences("MyGmailPreff", MODE_PRIVATE)

        var gymail = SharedPreferences.getString("Gmail","").toString()
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot!!.exists()){

                    for (data in snapshot.children){

                        var userdata = data.getValue(Userdata::class.java)
                        if (gymail== userdata!!.Gymail){
                            listofuser.add(userdata!!)
                        }
                    }
                    var adapterdelete = DeleteUseradapater(listofuser,application)
                    recyal.adapter=adapterdelete
                }


            }

            override fun onCancelled(error: DatabaseError) {


            }

        })

        recyal.layoutManager= LinearLayoutManager(this)
        recyal.setHasFixedSize(true)

    }
}