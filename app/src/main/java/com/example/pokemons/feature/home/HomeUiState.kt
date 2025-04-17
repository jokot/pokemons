package com.example.pokemons.feature.home

import com.example.pokemons.core.model.Pokemon

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data object Empty : HomeUiState
    data class Error(val message: String) : HomeUiState
    data class Success(val data: List<Pokemon>) : HomeUiState
}