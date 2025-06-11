package com.pgr208.mypokemon.data.repository

import android.util.Log
import com.pgr208.mypokemon.data.api.PokemonApiResponse
import com.pgr208.mypokemon.data.api.PokemonApiService
import com.pgr208.mypokemon.data.database.PokemonDao
import com.pgr208.mypokemon.data.model.Pokemon
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonRepository(private val pokemonDao: PokemonDao) {

    val allPokemons: Flow<List<Pokemon>> = pokemonDao.getAllPokemons()

    suspend fun insertPokemon(pokemon: Pokemon) {
        pokemonDao.insertPokemon(pokemon)
    }

    suspend fun deletePokemon(pokemon: Pokemon) {
        pokemonDao.deletePokemon(pokemon)
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(PokemonApiService::class.java)


    suspend fun getPokemonFromApi(nameOrId: String): PokemonApiResponse? {
        return try {
            val response = apiService.getPokemon(nameOrId)
            if (response.isSuccessful) {
                Log.d("PokemonRepository", "Fetched Pokémon: ${response.body()}")
                response.body()
            } else {
                Log.e("PokemonRepository", "API Error: ${response.code()}")
                null
            }
        } catch (e: Exception) {
            Log.e("PokemonRepository", "Network Error: ${e.message}")
            null
        }
    }
}
