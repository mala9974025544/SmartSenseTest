package com.smartsense.test.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
   @PrimaryKey val id: String,

    var avatar: String,

    var email: String,

    var first_name: String
): Parcelable
