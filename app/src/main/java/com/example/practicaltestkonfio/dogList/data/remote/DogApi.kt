package com.example.practicaltestkonfio.dogList.data.remote

import com.example.practicaltestkonfio.dogList.data.remote.respond.DogDto
import com.example.practicaltestkonfio.dogList.data.remote.respond.DogListDto
import retrofit2.http.GET


interface DogApi {
    @GET("dogs")
    suspend fun getDogsList(

    ): DogListDto


    companion object {
        const val BASE_URL = "https://jsonblob.com/api/1151549092634943488/"
        const val IMAGE_URL = "https://static.wikia.nocookie.net/isle-of-dogs/images/6/"
    }
}