package com.example.practicaltestkonfio.dogList.data.local.dog

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface DogDao {
    @Query("SELECT * FROM DogEntity")
     suspend fun getAllDogs(): List<DogEntity>

     @Upsert
     suspend fun upsertDogList(dogEntities: List<DogEntity>)


}