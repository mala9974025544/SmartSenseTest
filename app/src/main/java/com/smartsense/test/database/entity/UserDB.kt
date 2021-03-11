package com.smartsense.test.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserDB(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "avatar") val avatar: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "first_name") val first_name: String?
) {
    companion object {
        fun map(user: User): UserDB {
            return UserDB(
                id = user.id,
                avatar = user.avatar,
                email = user.email,
                first_name = user.first_name.trim().capitalize()
            )
        }

        fun mapList(userlist: List<User>): List<UserDB> {
            val userPostDBList = mutableListOf<UserDB>()
            for (recipe in userlist) {
                userPostDBList.add(map(recipe))
            }
            return userPostDBList
        }

    }
}