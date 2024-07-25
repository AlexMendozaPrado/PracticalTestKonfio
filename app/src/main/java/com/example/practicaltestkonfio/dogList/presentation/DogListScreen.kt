package com.example.practicaltestkonfio.dogList.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.practicaltestkonfio.dogList.presentation.components.DogItem

@Composable
fun DogsListScreen(
    dogListState: DogListState, navController: NavHostController
) {
    if (dogListState.dogs.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()

        }

    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(
                2
            ),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
        ) {
            items(dogListState.dogs.size) { index ->
                DogItem(
                    dog = dogListState.dogs[index]
                )
                Spacer(modifier = Modifier.height(16.dp))


            }
        }


    }


}