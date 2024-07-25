package com.example.practicaltestkonfio.dogList.data.local.dog

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(
    entities = [DogEntity::class],
    version = 4,
    exportSchema = false,
)

abstract class DogDatabase : RoomDatabase() {
    abstract val dogDao: DogDao



}