package com.example.mygym

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_show__gym__profile.*

class Show_Gym_Profile : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var databaseReference: DatabaseReference
    lateinit var databse : FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show__gym__profile)
        databse = FirebaseDatabase.getInstance()
        databaseReference = databse.getReference("Gym")
        val SharedPreferences : SharedPreferences = getSharedPreferences("MyGmailPreff", MODE_PRIVATE)

        var gyymail = SharedPreferences.getString("Gmail","").toString()

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children){

                    var userdata = data.getValue(gymData::class.java)
                    if (gyymail==userdata?.GymEmail){

                        tv_profile_gymName.text=userdata.GymName
                        tv_profile_GymAdminName.text=userdata.GymAdminNAme
                        tv_profile_GymAddress.text=userdata.GymAdd
                        tv_profile_GymPhone.text=userdata.GymConNumber
                        tv_profile_GymCity.text=userdata.GymCity
                        tv_profile_GymEmail.text=userdata.GymEmail

                    }
                }


            }

            override fun onCancelled(error: DatabaseError) = Unit


        } )

    }
}