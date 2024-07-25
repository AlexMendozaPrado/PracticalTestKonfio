package com.example.practicaltestkonfio.dogList.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaltestkonfio.dogList.domain.Repository.DogListRepository
import com.example.practicaltestkonfio.dogList.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogListViewModel @Inject constructor(
    private val dogListRepository: DogListRepository
) : ViewModel() {
    private var _dogListState = MutableStateFlow(DogListState())
    val dogListState = _dogListState.asStateFlow()

    init {
        getDogsList()

    }
    fun onEvent(event: DogListUIEvent) {
        when (event) {
            is DogListUIEvent.NavigateToDogListScreen -> {
                _dogListState.update {
                    it.copy(isCurrentDogsListScreen = !dogListState.value.isCurrentDogsListScreen)
                }
            }
        }
    }

    private fun getDogsList() {
        viewModelScope.launch {
            _dogListState.update {
                it.copy(isLoading = true)
            }
            dogListRepository.getDogsList().collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _dogListState.update {
                            it.copy(isLoading = false)
                        }
                    }

                    is Resource.Loading -> {
                        _dogListState.update {
                            it.copy(isLoading = result.isLoading)
                        }

                    }

                    is Resource.Success -> {
                        result.data?.let { dogList ->
                            _dogListState.update {
                                it.copy(
                                    // retorna la lista de perros
                                    dogs = dogList
                                )
                            }
                            Log.d("DogListViewModel", "Loaded dogs: $dogList")

                        }

                    }
                }
            }


        }
    }

}