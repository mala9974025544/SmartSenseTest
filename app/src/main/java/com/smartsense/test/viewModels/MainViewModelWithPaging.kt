package com.smartsense.test.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import com.smartsense.test.utils.BaseViewModel
import com.diegaspar.recipesbook.datasource.RecipeDataSourceFactory
import com.diegaspar.recipesbook.utils.pagedListConfig
import com.smartsense.test.database.entity.UserDB
import com.smartsense.test.utils.UserRepo
import kotlinx.coroutines.launch

class MainViewModelWithPaging(var repo: UserRepo) : BaseViewModel() {

    private val recipeDataSource = RecipeDataSourceFactory(repository = repo, scope = ioScope)

    val recipes = LivePagedListBuilder(recipeDataSource, pagedListConfig()).build()


    var offline = MutableLiveData<List<UserDB>>()
init {
    loadRecipesPersistence()
}
    fun loadRecipesPersistence() {
        ioScope.launch {
            val listRetrieved = repo.getAllRecipesPersistence()
            mainScope.launch {
                offline.value = listRetrieved
            }
        }
    }


  /*  var userPagedList: LiveData<PagedList<User>>
    private var liveDataSource: LiveData<UserDataSource>
    init {
        val itemDataSourceFactory = UserDataSource.UserDataSourceFactory()
        liveDataSource = itemDataSourceFactory.userLiveDataSource
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(UserDataSource.PAGE_SIZE)
            .build()
        userPagedList = LivePagedListBuilder(itemDataSourceFactory, config)
            .build()


    }*/

}