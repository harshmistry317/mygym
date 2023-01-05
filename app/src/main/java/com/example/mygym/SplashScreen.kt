package com.example.mygym

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView

class SplashScreen : AppCompatActivity() {

    lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        imageView=findViewById(R.id.imageView)
        var animat=AnimationUtils.loadAnimation(applicationContext,R.anim.alpha)
        imageView.animation=animat

        Handler().postDelayed(Runnable {
            var intent:Intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            finish()

        },4000)



    }
}