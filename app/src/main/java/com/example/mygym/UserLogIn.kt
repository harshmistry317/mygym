package com.example.mygym

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_admin_login.*
import kotlinx.android.synthetic.main.activity_user_log_in.*

class UserLogIn : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    var userlist = ArrayList<Userdata>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_log_in)
        auth = FirebaseAuth.getInstance()
        val currentuser = auth.currentUser

        if (currentuser != null) {
            var intent = Intent(this, user_dashboard::class.java)
            startActivity(intent)
            finish()
        }
        txt_forgot_password_user.setOnClickListener {
            auth.sendPasswordResetEmail(et_user_username.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Reset Link Will Sent on your Email", Toast.LENGTH_LONG).show()
                        }
                    }
                    }

        btn_user_login.setOnClickListener {
            var patterns = Patterns.EMAIL_ADDRESS
            if (TextUtils.isEmpty(et_user_username.text.toString())){
                et_user_username.setError("Enter Email first")
                et_user_username.requestFocus()
                return@setOnClickListener
            }/*else if(et_user_username.text.toString()!= patterns.toString()){
                et_user_username.setError("Email is not valid")
                et_user_username.requestFocus()
                return@setOnClickListener
            }*/else if (TextUtils.isEmpty(et_user_password.text.toString())){
                et_user_password.setError("Enter Password first")
                et_user_password.requestFocus()
                return@setOnClickListener
            }
           // if (et_user_username.text.toString() != (userlist[].userEmail))

            auth.signInWithEmailAndPassword(et_user_username.text.toString(),et_user_password.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            val SharedPreferences : SharedPreferences = this.getSharedPreferences("UserGmailPreff", MODE_PRIVATE)
                            var myeditor : SharedPreferences.Editor = SharedPreferences.edit()
                            myeditor.putString("Umail",et_user_username.text.toString())
                            myeditor.commit()
                            //put string
                            val intent = Intent(this,user_dashboard::class.java)
                            startActivity(intent)
                            finish()

                        }else{
                            Toast.makeText(applicationContext,"Something Went Wrong,Please try again", Toast.LENGTH_LONG).show()
                        }
                    }


        }
    }
}