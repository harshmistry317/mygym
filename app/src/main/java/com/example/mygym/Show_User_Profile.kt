package com.example.mygym

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_show__user__profile.*

class Show_User_Profile : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference
    lateinit var databse: FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show__user__profile)

        databse = FirebaseDatabase.getInstance()
        databaseReference = databse.getReference("GymUsers")


        val SharedPreferences : SharedPreferences = getSharedPreferences("UserGmailPreff", MODE_PRIVATE)

        var usmail = SharedPreferences.getString("Umail","").toString()

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children){

                    var userdata = data.getValue(Userdata::class.java)
                    if (usmail==userdata!!.userEmail){

                        tv_user_profile_name.text=userdata.username
                        tv_user_profile_phone.text = userdata.userphone
                        tv_user_profile_aadhar.text = userdata.userAdhar
                        tv_user_profile_aad.text = userdata.useradd
                        tv_user_profile_gender.text = userdata.usergender
                        tv_user_profile_age.text = userdata.userage
                        tv_user_profile_Height.text = userdata.userHeight
                        tv_user_profile_weight.text = userdata.userWeight
                        tv_user_profile_chest.text = userdata.userChest
                        tv_user_profile_Bicep.text = userdata.userBicep
                        tv_user_profile_Waist.text = userdata.userWaist
                        tv_user_profile_Thighs.text = userdata.userThighs
                        tv_user_profile_uemail.text = userdata.userEmail
                        tv_user_profile_gym_time.text = userdata.userGymtime


                    }
                }


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        } )



    }
}