package com.example.mygym

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        crd_user_login.setOnClickListener {
            val intent = Intent(this,UserLogIn::class.java)
            startActivity(intent)
            finish()

        }
        crd_admin_login.setOnClickListener {
            val intent = Intent(this,AdminLogin::class.java)
            startActivity(intent)
            finish()
        }



    }
}