package com.smartsense.test.repositary

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.smartsense.test.database.entity.UserDB
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