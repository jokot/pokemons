package com.example.pokemons.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonReponse(
    @SerialName("results")
    val results: List<PokemonNetwork>? = null
)