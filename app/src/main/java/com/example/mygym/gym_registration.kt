package com.example.mygym

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_gym_registration.*

class gym_registration : AppCompatActivity() {
    lateinit var DatabaseReference : DatabaseReference
    lateinit var auth: FirebaseAuth
    lateinit var gymname : EditText
    lateinit var adminname : EditText
    lateinit var adminConNumber : EditText
    lateinit var gymEmail : EditText
    lateinit var gymAdd : EditText
    lateinit var gymCity : EditText
    lateinit var gymPass :EditText
    lateinit var Register:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gym_registration)
        gymname = findViewById(R.id.et_gym_name)
        adminname = findViewById(R.id.et_gym_admin_name)
        adminConNumber = findViewById(R.id.et_admin_cont_number)
        gymEmail = findViewById(R.id.et_admin_email)
        gymAdd = findViewById(R.id.et_gym_address)
        gymCity = findViewById(R.id.et_gym_city)
        gymPass = findViewById(R.id.et_create_admin_password)
        Register = findViewById(R.id.btn_register)
        DatabaseReference = FirebaseDatabase.getInstance().getReference("Gym")
        auth = FirebaseAuth.getInstance()
        register()
        btn_admin_reg_Cancel.setOnClickListener {
            et_gym_name.setText("")
            et_gym_admin_name.setText("")
            et_admin_cont_number.setText("")
            et_admin_email.setText("")
            et_gym_address.setText("")
            et_gym_city.setText("")
            et_create_admin_password.setText("")
            var intent = Intent(applicationContext,AdminLogin::class.java)
            startActivity(intent)
            finish()


        }

    }
    private fun register(){

        Register.setOnClickListener {
            var patterns = Patterns.EMAIL_ADDRESS
            if (TextUtils.isEmpty(et_gym_name.text.toString())){
                et_gym_name.setError("Gym Name is Compulsory")
                et_gym_name.requestFocus()
                return@setOnClickListener
            }else if (TextUtils.isEmpty(et_gym_admin_name.text.toString())){
                et_gym_admin_name.setError("Admin Name is Compulsory")
                et_gym_admin_name.requestFocus()
                return@setOnClickListener
            }else if (TextUtils.isEmpty(et_admin_cont_number.text.toString())){
                et_admin_cont_number.setError(" Contect Number is Compulsory")
                et_admin_cont_number.requestFocus()
                return@setOnClickListener
            }else if(et_admin_cont_number.length()!= 10){
                et_admin_cont_number.setError("contect Number Mustbe in 10 digits")
                et_admin_cont_number.requestFocus()
                return@setOnClickListener
            }else if (TextUtils.isEmpty(et_gym_address.text.toString())){
                et_gym_address.setError("Gym Address is Compulsory")
                et_gym_address.requestFocus()
                return@setOnClickListener
            }else if (TextUtils.isEmpty(et_gym_city.text.toString())){
                et_gym_city.setError("Gym City is Compulsory")
                et_gym_city.requestFocus()
                return@setOnClickListener
            }else if(TextUtils.isEmpty(et_admin_email.text.toString())) {
                et_admin_email.setError("Email is Compulsory")
                et_admin_email.requestFocus()
                return@setOnClickListener

            }

            else if(et_admin_email.text.toString()!= patterns.toString()){
                et_admin_email.setError("Email is not valid")
                et_admin_email.requestFocus()
                return@setOnClickListener
            }else if(TextUtils.isEmpty(et_create_admin_password.text.toString())) {
                et_create_admin_password.setError("Password  is Compulsory")
                et_create_admin_password.requestFocus()
                return@setOnClickListener

            }else if(et_create_admin_password.length() < 6){
                et_create_admin_password.setError("Password mustbe in 6 or more charcter")
                et_create_admin_password.requestFocus()
                return@setOnClickListener
            }




            auth.createUserWithEmailAndPassword(gymEmail.text.toString(),gymPass.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            val currentuser = auth.currentUser
                            var id = DatabaseReference.push().key.toString()
                            //id = adminConNumber.text.toString()

                            var gymnamedata = gymname.text.toString()
                            var adminnamedata = adminname.text.toString()
                            var adminContectdata = adminConNumber.text.toString()
                            var gymEmaildata = gymEmail.text.toString()
                            var gymAdddata = gymAdd.text.toString()
                            var gymCitydata =gymCity.text.toString()
                            var gymPassdata = gymPass.text.toString()
                            var gymdata = gymData(id,gymnamedata,adminnamedata,adminContectdata,gymEmaildata,gymAdddata,gymCitydata,gymPassdata)
                            DatabaseReference.child(id).setValue(gymdata)
                            Toast.makeText(applicationContext,"Register Suceesfully",Toast.LENGTH_LONG).show()
                            var intent = Intent(applicationContext,MainActivity::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(applicationContext,"Register Fail",Toast.LENGTH_LONG).show()
                        }
                    }




        }


    }
    


}