package com.smartsense.test.ui.activities

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.smartsense.test.R
import com.smartsense.test.database.entity.User
import kotlinx.android.synthetic.main.activityuser_detai.*


class UserDetailActivity  : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activityuser_detai)
        if (intent.extras != null) {
            val user = intent.getParcelableExtra<Parcelable>("user") as User?
            if(user!=null)
                setupUi(user)
        }



    }

    private fun setupUi(user: User) {
        tvName.text = user.first_name
        tvEmail.text = user.email
        Glide.with(this)
            .load(user.avatar)
            //.placeholder(R.drawable.loading)
            .into(imgUser);
    }
}