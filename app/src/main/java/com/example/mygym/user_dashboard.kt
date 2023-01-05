package com.example.mygym

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_user_dashboard.*

class user_dashboard : AppCompatActivity() {

    lateinit var  auth: FirebaseAuth
    lateinit var databaseReference: DatabaseReference
    lateinit var databse : FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_dashboard)

        auth = FirebaseAuth.getInstance()
        databse = FirebaseDatabase.getInstance()
        databaseReference = databse.getReference("GymUsers")
        var gymref = databse.getReference("Gym")

        val SharedPreferences : SharedPreferences = getSharedPreferences("UserGmailPreff", MODE_PRIVATE)

        var usmail = SharedPreferences.getString("Umail","").toString()

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children){

                    var userdata = data.getValue(Userdata::class.java)
                    if (usmail==userdata!!.userEmail){

                        tv_user_dashbord_username.text=userdata.username


                    }
                }


            }

            override fun onCancelled(error: DatabaseError) {

            }


        } )
       /* gymref.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               for (data in snapshot.children){
                   var gdata = data.getValue(gymData::class.java)
                   var userdata = data.getValue(Userdata::class.java)
                   if (userdata!!.Gymail == gdata!!.GymEmail){
                       tv_user_dashbord_gymname.text = gdata.GymName
                   }
               }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })*/
        img_logout_userdash.setOnClickListener {

            val SharedPreference = getSharedPreferences("UserGmailPreff", MODE_PRIVATE)
            var edit = SharedPreference.edit()
            edit.clear()
            auth.signOut()
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        crd_dash_user_myprofile.setOnClickListener {

            var intent = Intent(this,Show_User_Profile::class.java)
            startActivity(intent)
        }
        crd_dash_user_editprofile.setOnClickListener {


            var intent = Intent(this,update_profile_user::class.java)
            startActivity(intent)
        }
        crd_dash_user_mydiet.setOnClickListener {
            var intent = Intent(this,Show_Deit_user::class.java)
            startActivity(intent)

        }
        crd_dash_user_myworkout.setOnClickListener {

            var intent = Intent(this,Show_user_Workout::class.java)
            startActivity(intent)
        }









    }
}