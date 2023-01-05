package com.example.mygym

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_update_profile_user.*

class update_profile_user : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference
    lateinit var databse : FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile_user)

        databse = FirebaseDatabase.getInstance()
        databaseReference = databse.getReference("GymUsers")

        val SharedPreferences : SharedPreferences = getSharedPreferences("UserGmailPreff", MODE_PRIVATE)

        var usmail = SharedPreferences.getString("Umail","").toString()

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children){

                    var userdata = data.getValue(Userdata::class.java)
                    if (usmail==userdata!!.userEmail){

                        var id = userdata.userid
                        et_updateuser_usergender.setText(userdata.usergender)
                        et_updateuser_userage.setText(userdata.userage)
                        et_updateuser_userheight.setText(userdata.userHeight)
                        et_updateuser_userweight.setText(userdata.userWeight)
                        et_updateuser_userchest.setText(userdata.userChest)
                        et_updateuser_userBiceps.setText(userdata.userBicep)
                        et_updateuser_userwaist.setText(userdata.userWaist)
                        et_updateuser_userThighs.setText(userdata.userThighs)

                        btn_updateuserfrmuser_update.setOnClickListener {

                            var map =  HashMap<String, Any>()
                            var upgender = et_updateuser_usergender.text.toString()
                            var upage = et_updateuser_userage.text.toString()
                            var upheight = et_updateuser_userheight.text.toString()
                            var upweight =  et_updateuser_userweight.text.toString()
                            var upchest = et_updateuser_userchest.text.toString()
                            var upbicep = et_updateuser_userBiceps.text.toString()
                            var upwaist = et_updateuser_userwaist.text.toString()
                            var upthigh = et_updateuser_userThighs.text.toString()
                            map.put("userid",id)
                            map.put("usergender",upgender)
                            map.put("userage",upage)
                            map.put("userHeight",upheight)
                            map.put("userWeight",upweight)
                            map.put("userChest",upchest)
                            map.put("userBicep",upbicep)
                            map.put("userWaist",upwaist)
                            map.put("userThighs",upthigh)

                            FirebaseDatabase.getInstance().getReference().child("GymUsers").child(id)
                                    .updateChildren(map).addOnCompleteListener {
                                        Toast.makeText(this@update_profile_user,"Update", Toast.LENGTH_LONG).show()
                                        var intent = Intent(applicationContext,user_dashboard::class.java)
                                        startActivity(intent)



                                    }




                        }



                    }
                }


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        } )


    }
}