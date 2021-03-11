package com.smartsense.test.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import com.smartsense.test.utils.BaseViewModel
import com.smartsense.test.repositary.RecipeDataSourceFactory
import com.diegaspar.recipesbook.utils.pagedListConfig
import com.smartsense.test.database.entity.UserDB
import com.smartsense.test.repositary.UserRepo
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
            val listRetrieved = repo.getAllUserPersistence()
            mainScope.launch {
                offline.value = listRetrieved
            }
        }
    }



}