package com.example.pokemons.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemons.core.data.DataState
import com.example.pokemons.core.data.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
): ViewModel() {
    private var _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        getPokemons()
    }

    private fun getPokemons() {
        viewModelScope.launch {
            pokemonRepository.getPokemons().collect { dataState ->
                when (dataState) {
                    is DataState.Loading -> HomeUiState.Loading
                    is DataState.Success -> if (dataState.data.isEmpty()) {
                        HomeUiState.Empty
                    } else {
                        HomeUiState.Success(dataState.data)
                    }
                    is DataState.Error -> HomeUiState.Error(dataState.message)
                }
            }
        }
    }
}