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
import kotlinx.android.synthetic.main.activity_gym_registration.*

class AdminLogin : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)


        auth = FirebaseAuth.getInstance()
        val currentuser = auth.currentUser

        // it for one time login

        if (currentuser != null) {
            var intent = Intent(this, admin_dashboard::class.java)
            startActivity(intent)
            finish()
        }
        txt_Reg_gym.setOnClickListener {
            val intent = Intent(this,gym_registration::class.java)
            startActivity(intent)
        }
        btn_admin_cancel.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        btn_admin_login.setOnClickListener {
            var patterns = Patterns.EMAIL_ADDRESS.toString()
            if (TextUtils.isEmpty(et_admin_username.text.toString())){
                et_admin_username.setError("Enter Email first")
                et_admin_username.requestFocus()
                return@setOnClickListener
            } /*else if(et_admin_username.text.toString()!= patterns){
                et_admin_username.setError("Email is not valid")
                et_admin_username.requestFocus()
                return@setOnClickListener
            }*/else if (TextUtils.isEmpty(et_admin_password.text.toString())){
                et_admin_password.setError("Enter Password first")
                et_admin_password.requestFocus()
            return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(et_admin_username.text.toString(),et_admin_password.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            //put string
                            val SharedPreferences : SharedPreferences = this.getSharedPreferences("MyGmailPreff", MODE_PRIVATE)
                            var myeditor : SharedPreferences.Editor = SharedPreferences.edit()
                            myeditor.putString("Gmail",et_admin_username.text.toString())
                            myeditor.commit()
                            val intent = Intent(this,admin_dashboard::class.java)
                            startActivity(intent)

                            finish()
                        }else{
                            Toast.makeText(applicationContext,"Something Went Wrong", Toast.LENGTH_LONG).show()
                        }
                    }

        }
        txt_forgot_password_admin.setOnClickListener {
             if (TextUtils.isEmpty(et_admin_username.text.toString())){
                 et_admin_username.setError("User Name is Empty please Provide")
                 et_admin_username.requestFocus()
            return@setOnClickListener
        }
            auth.sendPasswordResetEmail(et_admin_username.text.toString())
                    .addOnCompleteListener { task->
                        if (task.isSuccessful){
                            Toast.makeText(this,"Reset Link Will Sent on your Email",Toast.LENGTH_LONG).show()
                        }


           /* val view = layoutInflater.inflate(R.layout.dialog_forgot_password,null)
            val dialoginflater = AlertDialog.Builder(this).setView(view)
            var forgotuser : TextInputEditText = view.findViewById(R.id.et_dialog_fgpass_username)
            var passreset :Button = view.findViewById(R.id.btn_dialog_reset)
            var passcancel : Button = view.findViewById(R.id.btn_dialog_reset)
            var dialog = dialoginflater.create()
            dialog.show()*/

            /*passreset.setOnClickListener {
                forgotpass(forgotuser)
            }
            passcancel.setOnClickListener {
                val intent = Intent(this,AdminLogin::class.java)
                startActivity(intent)

                finish()
            }*/

        }

    }
   /* private fun forgotpass(forgotuser:TextInputEditText){

        auth.sendPasswordResetEmail(forgotuser.text.toString())
                .addOnCompleteListener { task->
                    if (task.isSuccessful){
                        Toast.makeText(this,"Reset Link Will Sent on your Email",Toast.LENGTH_LONG).show()
                    }
                }
    }*/
    }
}