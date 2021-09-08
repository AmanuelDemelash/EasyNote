package com.dmstechsolution.easynote

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.dmstechsolution.easynote.dashboard.Home
import com.dmstechsolution.easynote.onbording.Intro

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        supportActionBar!!.hide()

        val handler=Handler()
        handler.postDelayed({
            val perferance=getSharedPreferences("OnBording", Context.MODE_PRIVATE)
            val value=perferance.getBoolean("introfinsh",false)
            if (value){
                startActivity(Intent(this,Home::class.java))
            }
            else{
                startActivity(Intent(this,Intro::class.java))
            }


        },1500)
    }
}