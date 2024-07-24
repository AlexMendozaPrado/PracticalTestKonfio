package com.example.practicaltestkonfio.di

import com.example.practicaltestkonfio.dogList.data.Repository.DogListRepositoryImpl
import com.example.practicaltestkonfio.dogList.domain.Repository.DogListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindDogListRepository {
    @Binds
    @Singleton
    abstract fun bindDogListRepository(
        dogListRepositoryImpl: DogListRepositoryImpl
    ) : DogListRepository
}