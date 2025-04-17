package com.example.pokemons.core.di

import com.example.pokemons.core.data.PokemonRepository
import com.example.pokemons.core.data.PokemonRepositoryIml
import com.example.pokemons.core.network.NetworkDataSource
import com.example.pokemons.core.network.NetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPokemonRepository(
        pokemonRepositoryIml: PokemonRepositoryIml
    ): PokemonRepository
}