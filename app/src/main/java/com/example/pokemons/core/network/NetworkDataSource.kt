package com.example.pokemons.core.network

import com.example.pokemons.core.network.model.PokemonNetwork
import com.example.pokemons.core.network.service.ApiService
import javax.inject.Inject

interface NetworkDataSource {
    suspend fun getPokemons(): NetworkResult<List<PokemonNetwork>>
}

class NetworkDataSourceImpl @Inject constructor(
    private val apiService: ApiService
): NetworkDataSource {
    override suspend fun getPokemons(): NetworkResult<List<PokemonNetwork>> {
        return try {
            val response = apiService.getPokemons()
            val body = response.body()

            if (response.isSuccessful && body != null) {
                NetworkResult.Success(body.results.orEmpty())
            } else {
                NetworkResult.Error(response.message())
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.localizedMessage.orEmpty())
        }
    }
}