package com.ahmadmansour.DAO

import android.util.Log
import com.ahmadmansour.model.Tip
import com.parse.ParseObject

/**
 * Created by ahmad Mansour on 18,January,2020
 */

/*
* Class used to deal with the PARSE SERVER
* here we can do all the database operations, Create, Read, Update, Delete, and
* any other Queries ...
*/

class TipDAO {


    fun createRecord(tip: Tip) {

        /* Tip::class.java.name,
         *  this this is how we get the record name we want to connect to
         */
        var record = ParseObject(Tip::class.java.simpleName)
        record.put("title", tip.tipName)
        record.put("description", tip.tipDescription)


        record.saveInBackground { e ->
            if (e == null) {
                Log.i("App", "Record is Saved")
            } else {
                Log.e("App", "Record is not Saved" + e.printStackTrace())
            }
        }
    }
}