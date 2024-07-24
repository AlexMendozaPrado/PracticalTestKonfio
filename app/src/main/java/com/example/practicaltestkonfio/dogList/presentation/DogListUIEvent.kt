package com.example.practicaltestkonfio.dogList.presentation

sealed interface DogListUIEvent{
    object NavigateToDogListScreen : DogListUIEvent
}