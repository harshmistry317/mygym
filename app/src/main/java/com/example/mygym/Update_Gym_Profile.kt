package com.example.mygym

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_update__gym__profile.*

class Update_Gym_Profile : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference
    lateinit var databse : FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update__gym__profile)

        databse = FirebaseDatabase.getInstance()
        databaseReference = databse.getReference("Gym")
        val SharedPreferences : SharedPreferences = getSharedPreferences("MyGmailPreff", MODE_PRIVATE)

        var gyymail = SharedPreferences.getString("Gmail","").toString()

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children){

                    var userdata = data.getValue(gymData::class.java)
                    if (gyymail==userdata!!.GymEmail){

                        var id1 = userdata.id
                        et_updategym_gymname.setText(userdata.GymName)
                        et_updategym_adminname.setText(userdata.GymAdminNAme)
                        et_updategym_phone.setText(userdata.GymConNumber)
                        et_updategym_Gymaddress.setText(userdata.GymAdd)
                        et_updategym_Gymcity.setText(userdata.GymCity)
                        btn_updategympro_updte.setOnClickListener {

                            var map =  HashMap<String, Any>()
                            var upgymname = et_updategym_gymname.text.toString()
                            var upgymadminname = et_updategym_adminname.text.toString()
                            var upgymphone = et_updategym_phone.text.toString()
                            var upgymadd = et_updategym_Gymaddress.text.toString()
                            var upgymcity = et_updategym_Gymcity.text.toString()
                            map.put("id",id1)
                            map.put("GymName",upgymname)
                            map.put("GymAdminNAme",upgymadminname)
                            map.put("GymConNumber",upgymphone)
                            map.put("GymAdd",upgymadd)
                            map.put("GymCity",upgymcity)
                            FirebaseDatabase.getInstance().getReference().child("Gym").child(id1)
                                    .updateChildren(map).addOnCompleteListener {
                                        Toast.makeText(this@Update_Gym_Profile,"Updated",Toast.LENGTH_LONG).show()
                                        var intent = Intent(applicationContext,admin_dashboard::class.java)
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