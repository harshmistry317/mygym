package com.example.mygym

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_show_user__workout.*

class Show_user_Workout : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference
    lateinit var databse: FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_user__workout)
        databse = FirebaseDatabase.getInstance()
        databaseReference = databse.getReference("GymUsers")


        val SharedPreferences : SharedPreferences = getSharedPreferences("UserGmailPreff", MODE_PRIVATE)

        var usmail = SharedPreferences.getString("Umail","").toString()

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children){

                    var userdata = data.getValue(Userdata::class.java)
                    if (usmail==userdata!!.userEmail){

                        tv_workout_mon.setText(userdata.workmon)
                        tv_workout_tues.setText(userdata.worktues)
                        tv_workout_wed.setText(userdata.workwed)
                        tv_workout_thus.setText(userdata.workthus)
                        tv_workout_fri.setText(userdata.workfri)
                        tv_workout_sat.setText(userdata.worksat)
                        tv_workout_sun.setText(userdata.worksun)




                    }
                }


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        } )

    }
}