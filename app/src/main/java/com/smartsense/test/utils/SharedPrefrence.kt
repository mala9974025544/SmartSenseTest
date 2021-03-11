package com.smartsense.test.utils

import android.content.Context
import android.content.SharedPreferences
import com.smartsense.test.R


object SharedPrefrence {

    //VARIABLES

    const val USER_EMAIL = "user_email"

    private fun getSharedPref(context: Context?): SharedPreferences? {
        return context?.getSharedPreferences(
            context?.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
    }



    fun setUserEmail(context: Context?, email:String){
        val sharedPref = getSharedPref(context)
        val editor = sharedPref!!.edit()
        editor.putString(USER_EMAIL, email)
        editor.apply()
    }
    fun getuserEmail(context: Context?):String?{
        val sharedPref = getSharedPref(context)
        return sharedPref!!.getString(USER_EMAIL, "")
    }

}
