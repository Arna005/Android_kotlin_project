package com.pgr208.mypokemon.data.database

import androidx.room.*
import com.pgr208.mypokemon.data.model.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: Pokemon): Long

    @Query("SELECT * FROM pokemon_table ORDER BY id DESC")
    fun getAllPokemons(): Flow<List<Pokemon>>

    @Delete
    suspend fun deletePokemon(pokemon: Pokemon)
}