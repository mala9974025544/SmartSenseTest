package com.smartsense.test.ui.activities

import android.content.Intent
import android.os.Bundle
import com.smartsense.test.R
import kotlinx.coroutines.*
import spencerstudios.com.bungeelib.Bungee
import kotlin.coroutines.CoroutineContext

class SplashActivity : BaseActivity(), CoroutineScope {


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        fullScreen()
        launch {
            delay(2000)
            withContext(Dispatchers.Main) {
                updateUI()
            }
        }

    }


    private fun updateUI() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        Bungee.slideLeft(this)

    }

    override fun onBackPressed() {
        finish()
    }

}
