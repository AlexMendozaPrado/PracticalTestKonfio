package com.example.practicaltestkonfio.dogList.util

sealed class Screen(val rout: String) {
    object Home : Screen("main")
    object DogList : Screen("Dog")
}