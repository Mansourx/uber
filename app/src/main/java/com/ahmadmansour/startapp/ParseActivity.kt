package com.ahmadmansour.startapp

import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_parse.*

class ParseActivity : AppCompatActivity() {

    var progressDialog: ProgressDialog? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parse)
        progressDialog = ProgressDialog(this)
        progressDialog?.setCancelable(true)
        progressDialog?.setMessage("Wait for loading")
        progressDialog?.show()
    }

    fun parseButtonClick(view: View) {
        var btn = view as Button

        // CRUD create, read, update, delete from the server
        when (btn) {
            gbtn1 ->
                // Check the connection
                if (isNetworkConnected()) {
                    Log.e("connection:", isNetworkConnected().toString())
                } else {
                    AlertDialog.Builder(this)
                            .setTitle("No Internet")
                            .setMessage("Check your internet connection!")
                            .setPositiveButton(android.R.string.ok) { dialogInterface: DialogInterface, i: Int ->
                                // Lunch your code
                            }
                            .setNegativeButton(android.R.string.cancel) { dialogInterface: DialogInterface, i: Int ->
                                // Lunch your code on cancel
                            }
                            .setIcon(android.R.drawable.ic_dialog_alert).show()
                }
//            gbtn2 ->
//            // create in parse
//
//            gbtn3 ->
//            // read in parse
//
//            gbtn4 ->
//            // update in parse
//
//            gbtn5 ->
//            // delete in parse
//
//            gbtn6 ->
//            // query in parse

        }
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
