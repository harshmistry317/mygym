package com.example.mygym

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_admin_dashboard.*

class admin_dashboard : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var databaseReference: DatabaseReference
    lateinit var databse : FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)
        auth = FirebaseAuth.getInstance()
        databse = FirebaseDatabase.getInstance()
        databaseReference = databse.getReference("Gym")
        val SharedPreferences : SharedPreferences = getSharedPreferences("MyGmailPreff", MODE_PRIVATE)

        var gyymail = SharedPreferences.getString("Gmail","").toString()



        val user = auth.currentUser
        //val userrferece = databaseReference
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               for (data in snapshot.children){

                   var userdata = data.getValue(gymData::class.java)
                   if (gyymail==userdata!!.GymEmail){

                       tv_admin_dash_gymname.text=userdata.GymName

                   }
               }


            }

            override fun onCancelled(error: DatabaseError) {

            }


        } )










       
        //this for show gym name on dash board

        //this for add user card view
        crd_dash_Admin_addUser.setOnClickListener {
            var intent = Intent(this,add_user::class.java)
            startActivity(intent)
        }

        crd_dash_Admin_show_allUser.setOnClickListener {
            var intent = Intent(this,ShowAllUser::class.java)
            startActivity(intent)
        }

        crd_dash_Admin_updateUser.setOnClickListener {

            var intent = Intent(this,UpdateUser_admin::class.java)
            startActivity(intent)

        }
        crd_dash_Admin_deleteUser.setOnClickListener {
            var intent = Intent(this,Delete_User::class.java)
            startActivity(intent)

        }
        crd_dash_admin_gymprofile.setOnClickListener {
            var intent = Intent(this,Show_Gym_Profile::class.java)
            startActivity(intent)


        }

        crd_dash_Admin_edit_gym_profile.setOnClickListener {
            var intent = Intent(this,Update_Gym_Profile::class.java)
            startActivity(intent)


        }
        crd_dash_Admin_set_user_diet.setOnClickListener {
            var intent = Intent(this,set_deit_admin::class.java)
            startActivity(intent)

        }
        crd_dash_Admin_set_user_workout.setOnClickListener {
            var intent = Intent(this,Set_workout_admin::class.java)
            startActivity(intent)


        }

        //this for logout


        img_logout.setOnClickListener {
           val SharedPreferences  = getSharedPreferences("MyGmailPreff", MODE_PRIVATE)
           //var gyymail = SharedPreferences.getString("Gmail","").toString()
            var edit = SharedPreferences.edit()
            edit.clear()
            auth.signOut()
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}