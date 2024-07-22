package com.example.practicaltestkonfio.dogList.data.local.dog

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DogEntity(
    val age: Int,
    val description: String,
    val dogName: String,
    val image: String,
)