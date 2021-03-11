package com.diegaspar.recipesbook.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.smartsense.test.database.entity.User
import com.smartsense.test.database.entity.UserDB
import com.smartsense.test.utils.UserDataSource
import com.smartsense.test.utils.UserRepo
import kotlinx.coroutines.CoroutineScope

class RecipeDataSourceFactory(
    private val repository: UserRepo,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, UserDB>() {

    val source = MutableLiveData<UserDataSource>()

    override fun create(): DataSource<Int, UserDB> {
        val source = UserDataSource(repository, scope)
        this.source.postValue(source)
        return source
    }


    fun getSource() = source.value



}