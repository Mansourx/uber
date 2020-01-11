package com.ahmadmansour.startapp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.ahmadmansour.startapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showTips(view: View) {
        Log.d("App","show Tips")

        var myIntent = Intent(this.baseContext, TipsActivity::class.java)
        startActivity(myIntent)

    }
}
