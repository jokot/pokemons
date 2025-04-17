package com.example.pokemons.core.data

import com.example.pokemons.core.model.Pokemon
import com.example.pokemons.core.network.NetworkDataSource
import com.example.pokemons.core.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface PokemonRepository {
    fun getPokemons(): Flow<DataState<List<Pokemon>>>
}

class PokemonRepositoryIml @Inject constructor(
    private val networkDataSource: NetworkDataSource
): PokemonRepository {

    companion object {
        const val IMAGE_BASE_URL = "https://img.pokemondb.net/artwork/large/"
    }

    override fun getPokemons(): Flow<DataState<List<Pokemon>>> = flow {
        emit(DataState.Loading)


        when (val networkResult = networkDataSource.getPokemons()) {
            is NetworkResult.Success -> {
                val pokemons = networkResult.data.map {
                    Pokemon(
                        name = it.name.orEmpty(),
                        imageUrl = "${IMAGE_BASE_URL}${it.name.orEmpty()}.jpg"
                    )
                }
                emit(DataState.Success(pokemons))
            }
            is NetworkResult.Error -> {
                emit(DataState.Error(networkResult.message))
            }
        }
    }
}