package com.pgr208.mypokemon.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pgr208.mypokemon.viewmodel.PokemonViewModel

@Composable
fun ShowPokemonScreen(viewModel: PokemonViewModel) {
    val pokemons by viewModel.allPokemons.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        // List of new Pokemon
        Text(text = "Saved Pokémon", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(pokemons) { pokemon ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Name: ${pokemon.name}", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Type: ${pokemon.type}", style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.height(8.dp))

                        // Delete button
                        Button(
                            onClick = { viewModel.deletePokemon(pokemon) },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                        ) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
}