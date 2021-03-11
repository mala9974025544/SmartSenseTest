package com.smartsense.test.repositary

import com.smartsense.test.database.dao.UserDao
import com.smartsense.test.database.entity.User
import com.smartsense.test.database.entity.UserDB
import com.smartsense.test.services.NetworkApiWithPaging

class UserRepo(private val recipesService: NetworkApiWithPaging, private val dao: UserDao) {


    suspend fun saveUserPersistence(recipe:List<UserDB>) {
        dao.insertList(recipe)


    }
    suspend fun updateUserPersistence(recipe:List<UserDB>) {
        dao.update(recipe)

    }
    suspend fun getAllUserPersistence(): List<UserDB> {
        return dao.findAllUsers()
    }

    suspend fun deleteRecipePersistence(recipe: UserDB) {
        dao.delete(recipe)
    }
}