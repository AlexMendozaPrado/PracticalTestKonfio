package com.example.practicaltestkonfio.dogList.data.Repository

import com.example.practicaltestkonfio.dogList.data.local.dog.DogDatabase
import com.example.practicaltestkonfio.dogList.data.remote.DogApi
import com.example.practicaltestkonfio.dogList.domain.Repository.DogListRepository
import com.example.practicaltestkonfio.dogList.domain.model.Dog
import javax.inject.Inject

class DogListRepositoryImpl @Inject constructor(
    private val dogApi :DogApi,
    private val dogDatabase: DogDatabase
): DogListRepository {
    suspend fun getDogsList(): List<Dog>

}