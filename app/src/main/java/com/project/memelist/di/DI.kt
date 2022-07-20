package com.project.memelist.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.project.memelist.api.MemeRepositoryImpl
import com.project.memelist.api.MemeService
import com.project.memelist.viewModel.MemeViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// Dependency Injection
//Dagger, Hilt, Koin <-- Android tools for DI
// Manual DI
object DI {
    // "object" <- How Kotlin can make a singleton
    private val service = Retrofit.Builder()
        .baseUrl("https://alpha-meme-maker.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideHttpClient())
        .build()
        .create(MemeService::class.java)

    private fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private fun provideRepository() = MemeRepositoryImpl(service)
    private fun provideDispatcher() = Dispatchers.IO

    fun provideViewModel(storeOwner: ViewModelStoreOwner): MemeViewModel {
        return ViewModelProvider(storeOwner, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MemeViewModel(provideRepository(), provideDispatcher()) as T
            }
        })[MemeViewModel::class.java]
    }
}