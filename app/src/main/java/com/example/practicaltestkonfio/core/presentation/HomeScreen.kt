package com.example.practicaltestkonfio.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.Upcoming
import androidx.compose.material.icons.sharp.Apps
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practicaltestkonfio.dogList.domain.model.Dog
import com.example.practicaltestkonfio.dogList.presentation.DogListUIEvent
import com.example.practicaltestkonfio.dogList.presentation.DogListViewModel
import com.example.practicaltestkonfio.dogList.presentation.DogsListScreen
import com.example.practicaltestkonfio.dogList.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val dogListViewModel = hiltViewModel<DogListViewModel>()
    val dogListState = dogListViewModel.dogListState.collectAsState().value
    val bottomNavController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = if(dogListState.isCurrentDogsListScreen)
                    "Dogs We Love "
                else
                    "Home Screen",
                    fontSize = 20.sp
                )

            },
                modifier = Modifier.shadow(2.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    MaterialTheme.colorScheme.inverseOnSurface

                )

            )

        },
        bottomBar = {
            BottomNavigationBar(bottomNavController, dogListViewModel::onEvent)
        }
    ) {
        Box(modifier = Modifier
            .padding(it)
            .fillMaxSize()
        ){
            NavHost(navController = bottomNavController,
                startDestination = Screen.DogList.rout
            ){
                composable(Screen.DogList.rout){
                    DogsListScreen(dogListState=dogListState,navController=navController)

                }
            }

        }

    }




}
@Composable
fun BottomNavigationBar(
    bottomNavController: NavHostController,
    onEvent: (DogListUIEvent) -> Unit
) {
    val items = listOf(
        BottomItem(
            title = "Home",
            icon = Icons.Rounded.Home

        ),
        BottomItem(
            title = "Get DogList",
            icon = Icons.Sharp.Apps
        )
    )
    val selected = rememberSaveable {
        mutableIntStateOf(0)
    }
    NavigationBar {
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            items.forEachIndexed { index, bottomItem ->
                NavigationBarItem(selected.intValue == index,
                    onClick = {
                        selected.intValue = index
                        when (selected.intValue) {
                            0 -> {
                                onEvent(DogListUIEvent.NavigateToDogListScreen)
                                bottomNavController.popBackStack()
                                bottomNavController.navigate(Screen.Home.rout)

                            }

                            1 -> {
                                onEvent(DogListUIEvent.NavigateToDogListScreen)
                                bottomNavController.popBackStack()
                                bottomNavController.navigate(Screen.DogList.rout)

                            }


                        }
                    },
                    icon = {
                        Icon(
                            imageVector = bottomItem.icon,
                            contentDescription = bottomItem.title,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    label = {
                        Text(
                            text = bottomItem.title,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }


                )
            }

        }
    }


}

data class BottomItem(
    val title: String,
    val icon: ImageVector


) {

}