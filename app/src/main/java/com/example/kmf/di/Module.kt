package com.example.kmf.di

import android.content.Context
import androidx.room.Room
import com.example.kmf.api.MainApi
import com.example.kmf.data.Database
import com.example.kmf.data.UserDAO
import com.example.kmf.network.MainRepository
import com.example.kmf.vm.AuthViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@DelicateCoroutinesApi
val providerModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideApi(get()) }
    single { provideRepo(get(), get()) }
}

val databaseModule = module {
    single { provideRoom(get()) }
    single { provideUserDAO(get()) }
}

@DelicateCoroutinesApi
val viewModelModule = module {
    viewModel {
        AuthViewModel(get())
    }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl("https://petstore.swagger.io/v2/")
        .client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(): OkHttpClient {
    val logger = HttpLoggingInterceptor()
    logger.level = (HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient()
        .newBuilder()
        .addInterceptor(logger)
        .build()
}

@DelicateCoroutinesApi
fun provideRepo(
    api: MainApi,
    userDAO: UserDAO
): MainRepository =
    MainRepository(api, userDAO)

fun provideApi(retrofit: Retrofit): MainApi = retrofit.create(MainApi::class.java)

fun provideRoom(context: Context): Database {
    return Room.databaseBuilder(
        context,
        Database::class.java,
        "room"
    ).fallbackToDestructiveMigration()
     .build()
}

fun provideUserDAO(database: Database): UserDAO {
    return database.userDao()
}