package com.example.pokemons.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonNetwork(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)