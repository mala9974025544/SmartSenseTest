package com.smartsense.test.repositary

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.smartsense.test.database.entity.UserDB
import com.smartsense.test.database.entity.UserDB.Companion.mapList
import com.smartsense.test.services.ServiceClassWithPaging
import com.smartsense.test.datamodel.UserResponse
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Morris on 03,June,2019
 */
class UserDataSource(private val repository: UserRepo,
                     private val scope: CoroutineScope
): PageKeyedDataSource<Int, UserDB>() {


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, UserDB?>
    ) {
        Log.d("Log", "loadInitial")
        //val service = ApiServiceBuilder.buildService(ApiService::class.java)
        val call = ServiceClassWithPaging.apiService.getUsers(FIRST_PAGE)

        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    apiResponse.let {



                        saveRecipePersistence( mapList(apiResponse.users!!))

                        callback.onResult( mapList(apiResponse.users!!),null, FIRST_PAGE + 1)
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            }
        })
    }


    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int?, UserDB?>) {
        val call = ServiceClassWithPaging.apiService.getUsers(params.key)
        Log.d("Log", "loadBefore")

        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!

                    val key = if (params.key > 1) params.key - 1 else 0
                    apiResponse.let {

                        callback.onResult(mapList( apiResponse.users!!), key)

                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int?, UserDB?>) {
        val call = ServiceClassWithPaging.apiService.getUsers(params.key)
        Log.d("Log", "loadAfter")
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!

                    val key = params.key + 1
                    apiResponse.let {
                       // updateUserPersistence( mapList(apiResponse.users!!))
                        callback.onResult(mapList(apiResponse.users!!), key)
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            }
        })
    }

    //this will be called once to load the initial data
    companion object {
        const val PAGE_SIZE = 6
        const val FIRST_PAGE = 1
    }


    fun saveRecipePersistence(recipe: List<UserDB>) {
        scope.launch {
            recipe?.let { repository.saveUserPersistence(it) }
        }
    }

    fun updateUserPersistence(recipe: List<UserDB>) {
        scope.launch {
            recipe?.let { repository.updateUserPersistence(it) }
        }
    }

}