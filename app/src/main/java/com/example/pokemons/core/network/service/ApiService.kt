package com.example.pokemons.core.network.service

import com.example.pokemons.core.network.model.PokemonReponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("pokemon")
    suspend fun getPokemons(): Response<PokemonReponse>

}