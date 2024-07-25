package com.example.practicaltestkonfio.di

import android.app.Application
import androidx.room.Room
import com.example.practicaltestkonfio.dogList.data.local.dog.DogDatabase
import com.example.practicaltestkonfio.dogList.data.remote.DogApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    private val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply{
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()


    @Singleton
    @Provides
    fun providesDogApi(): DogApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(DogApi.BASE_URL)
            .client(client)
            .build()
            .create(DogApi::class.java)
    }
    @Provides
    @Singleton
    fun providesMovieDatabase(app: Application): DogDatabase {
        return Room.databaseBuilder(
            app,
            DogDatabase::class.java,
            "dog_db"
        )
            .fallbackToDestructiveMigration()
            .build()

    }



}