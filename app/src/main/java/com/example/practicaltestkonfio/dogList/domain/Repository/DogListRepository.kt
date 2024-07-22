package com.example.practicaltestkonfio.dogList.domain.Repository

import com.example.practicaltestkonfio.dogList.domain.model.Dog
import com.example.practicaltestkonfio.dogList.util.Resource
import kotlinx.coroutines.flow.Flow

interface DogListRepository {
    suspend fun getDogsList(): Flow<Resource<List<Dog>>>

}