package com.example.practicaltestkonfio.dogList.data.local.dog

import androidx.room.Dao
import androidx.room.Query

@Dao
interface DogDao {
    @Query("SELECT * FROM DogEntity")
    fun getAllDogs(): List<DogEntity>

}