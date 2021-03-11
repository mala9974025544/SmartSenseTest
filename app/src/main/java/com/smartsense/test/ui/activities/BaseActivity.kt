package com.smartsense.test.ui.activities

import android.app.ProgressDialog
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.smartsense.test.R


open class BaseActivity : AppCompatActivity() {



    fun fullScreen(){
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }


}