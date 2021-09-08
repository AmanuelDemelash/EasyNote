package com.dmstechsolution.easynote.onbording

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dmstechsolution.easynote.R
import com.dmstechsolution.easynote.R.layout.activity_intro

class Intro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_intro)
        supportActionBar!!.hide()
    }
}