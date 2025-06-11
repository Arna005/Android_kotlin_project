package com.pgr208.mypokemon.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.pgr208.mypokemon.data.api.PokemonApiResponse
import com.pgr208.mypokemon.data.database.PokemonDatabase
import com.pgr208.mypokemon.data.model.Pokemon
import com.pgr208.mypokemon.data.repository.PokemonRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PokemonViewModel(application: Application) : AndroidViewModel(application) {

    // Repository
    private val repository: PokemonRepository
    val allPokemons: StateFlow<List<Pokemon>>
    private val _searchedPokemon = MutableStateFlow<PokemonApiResponse?>(null)
    val searchedPokemon: StateFlow<PokemonApiResponse?> = _searchedPokemon

    init {

        // Set up database and repository
        val pokemonDao = PokemonDatabase.getDatabase(application).pokemonDao()
        repository = PokemonRepository(pokemonDao)

        // Saved Pokémon
        allPokemons = repository.allPokemons
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    }

    // New Pokémon
    fun insertPokemon(pokemon: Pokemon) = viewModelScope.launch {
        repository.insertPokemon(pokemon)
    }

    // Delete Pokémon
    fun deletePokemon(pokemon: Pokemon) = viewModelScope.launch {
        repository.deletePokemon(pokemon)
    }

    // Search for Pokémon from PokeAPI
    fun searchPokemon(nameOrId: String) = viewModelScope.launch {
        _searchedPokemon.value = repository.getPokemonFromApi(nameOrId)
    }
}