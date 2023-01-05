package com.example.mygym

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_show__deit_user.*

class Show_Deit_user : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference
    lateinit var databse: FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show__deit_user)

        databse = FirebaseDatabase.getInstance()
        databaseReference = databse.getReference("GymUsers")


        val SharedPreferences : SharedPreferences = getSharedPreferences("UserGmailPreff", MODE_PRIVATE)

        var usmail = SharedPreferences.getString("Umail","").toString()

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children){

                    var userdata = data.getValue(Userdata::class.java)
                    if (usmail==userdata!!.userEmail){

                        et_showdeit_monday.setText(userdata.deitmon)
                        et_showdeit_tuesday.setText(userdata.deittues)
                        et_showdeit_wednesday.setText(userdata.deitwed)
                        et_showdeit_thursday.setText(userdata.deitthus)
                        et_showdeit_friday.setText(userdata.dietfri)
                        et_showdeit_satday.setText(userdata.dietsat)
                        et_showdeit_sunday.setText(userdata.dietsun)




                    }
                }


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        } )
    }
}