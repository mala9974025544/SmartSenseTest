package com.smartsense.test.di

import android.app.Application
import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.smartsense.test.database.AppDatabase
import com.smartsense.test.database.dao.UserDao
import com.smartsense.test.services.NetworkApiWithPaging
import com.smartsense.test.repositary.UserRepo
import com.smartsense.test.viewModels.MainViewModelWithPaging
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val viewModelModule = module {
    fun provideUserViewodel(user: UserRepo): MainViewModelWithPaging {
        return MainViewModelWithPaging(user)
    }

    single { provideUserViewodel(get()) }
}
private const val BASE_URL = "https://reqres.in/api/"
val apiModule = module {
    fun provideUserApi(retrofit: Retrofit): NetworkApiWithPaging {
        return retrofit.create(NetworkApiWithPaging::class.java)
    }

    single { provideUserApi(get()) }
}

val netModule = module {
    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    fun provideHttpClient(cache: Cache): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .cache(cache)

        return okHttpClientBuilder.build()
    }

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }


    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(factory))

            .client(client)
            .build()
    }

    single { provideCache(androidApplication()) }
    single { provideHttpClient(get()) }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }

}

val databaseModule = module {

    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "eds.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }


    fun provideDao(database: AppDatabase): UserDao {
        return database.userDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}

val repositoryModule = module {
    fun provideUserRepository(api: NetworkApiWithPaging, dao: UserDao): UserRepo {
        return UserRepo(api, dao)
    }

    single { provideUserRepository(get(), get()) }
}