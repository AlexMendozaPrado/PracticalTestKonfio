package com.example.practicaltestkonfio.dogList.data.remote

import com.example.practicaltestkonfio.dogList.data.remote.respond.DogDto
import retrofit2.http.GET


interface DogApi {
    @GET("dogs")
    suspend fun getDogsList(

    ): List<DogDto>


    companion object {
        const val BASE_URL = "https://jsonblob.com/api/1151549092634943488"
    }
}