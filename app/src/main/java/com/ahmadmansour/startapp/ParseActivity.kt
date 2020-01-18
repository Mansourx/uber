package com.ahmadmansour.startapp

import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import com.ahmadmansour.DAO.TipDAO
import com.ahmadmansour.model.Tip
import kotlinx.android.synthetic.main.activity_parse.*

class ParseActivity : AppCompatActivity() {

    var progressDialog: ProgressDialog? = null
    var tipDAO: TipDAO?= null
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parse)
        progressDialog = ProgressDialog(this)
        progressDialog?.setCancelable(true)
        progressDialog?.setMessage("Wait for loading")
        tipDAO = TipDAO()

    }

    fun parseButtonClick(view: View) {
        var btn = view as Button

        // CRUD create, read, update, delete from the server
        when (btn) {
            gbtn1 ->
            {
                // Check the connection
                if (isNetworkConnected()) {
                    Log.e("connection:", isNetworkConnected().toString())
                } else {
                    showAlertDialog("No Internet", "Check your internet connection!")
                }
            }

            gbtn2 -> {
                // create in parse
                progressDialog?.show()
                var tip = Tip(1, "title", "this is a Description", "")
                tipDAO?.createRecord(tip)
                progressDialog?.cancel()
            }
//            gbtn3 ->
//            // read in parse
//
            gbtn4 -> {
                // update in parse
                var tip = Tip(1, "The Boss", "this is Ahmad The Description", "ff")
                tipDAO?.updateRecord(tip)
            }

//
//            gbtn5 ->
//            // delete in parse
//
//            gbtn6 ->
//            // query in parse

        }
    }

    private fun showAlertDialog(title: String, msg: String) {
        AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(android.R.string.ok) { dialogInterface: DialogInterface, i: Int ->
                    // Lunch your code
                }
                .setNegativeButton(android.R.string.cancel) { dialogInterface: DialogInterface, i: Int ->
                    // Lunch your code on cancel
                }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
    }



    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
