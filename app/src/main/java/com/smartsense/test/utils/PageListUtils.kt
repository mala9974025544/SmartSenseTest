package com.diegaspar.recipesbook.utils

import androidx.paging.PagedList
import com.smartsense.test.utils.UserDataSource

fun pagedListConfig() = PagedList.Config.Builder()
      .setEnablePlaceholders(true)
    .setPageSize(UserDataSource.PAGE_SIZE)
    .build()
/*
val itemDataSourceFactory = UserDataSource.UserDataSourceFactory()
liveDataSource = itemDataSourceFactory.userLiveDataSource
val config = PagedList.Config.Builder()
    .setEnablePlaceholders(true)
    .setPageSize(UserDataSource.PAGE_SIZE)
    .build()
userPagedList = LivePagedListBuilder(itemDataSourceFactory, config)
.build()*/
