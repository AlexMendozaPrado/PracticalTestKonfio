package com.example.practicaltestkonfio.dogList.presentation

import com.example.practicaltestkonfio.dogList.domain.model.Dog

data class DogListState (
    val dogs : List<Dog> = emptyList(),
    val isLoading : Boolean = false

)
