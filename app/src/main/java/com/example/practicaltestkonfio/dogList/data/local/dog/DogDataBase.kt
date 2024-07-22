package com.example.practicaltestkonfio.dogList.data.local.dog

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [DogEntity::class],
    version = 1,
    exportSchema = false
)

abstract class DogDatabase : RoomDatabase() {
    abstract val dogDao: DogDao


}