package com.example.mygym

import android.app.Person
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import java.nio.file.attribute.AclEntry
import java.util.*
import java.util.stream.Stream

class ShowAllUser : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference
    lateinit var recyal : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_all_user)
       // var listofdata =ArrayList<ModelShowalluser>()
        //var adapter = ShowalluserAdapter(listofdata)
        var listofuser = ArrayList<Userdata>()
        recyal = findViewById(R.id.recyclerviewShowalluser)
        databaseReference=FirebaseDatabase.getInstance().getReference("GymUsers")
        val SharedPreferences : SharedPreferences = getSharedPreferences("MyGmailPreff", MODE_PRIVATE)

        var gymail = SharedPreferences.getString("Gmail","").toString()


        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot!!.exists()){

                    for (data in snapshot.children){

                        var userdata = data.getValue(Userdata::class.java)
                        if (gymail== userdata!!.Gymail){
                            listofuser.add(userdata!!)
                        }
                    }
                    var adaptershowall = ShowalluserAdapter(listofuser)
                    recyal.adapter=adaptershowall
                }


            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        recyal.layoutManager=LinearLayoutManager(applicationContext)
        recyal.setHasFixedSize(true)



    }









    }


