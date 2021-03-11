package com.smartsense.test.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class User(


    @PrimaryKey val id: String,

    val avatar: String,

    val email: String,

    val first_name: String
)
