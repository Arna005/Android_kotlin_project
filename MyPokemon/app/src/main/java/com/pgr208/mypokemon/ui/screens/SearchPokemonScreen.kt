package com.pgr208.mypokemon.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.pgr208.mypokemon.viewmodel.PokemonViewModel

@Composable
fun SearchPokemonScreen(viewModel: PokemonViewModel) {
    var searchQuery by remember { mutableStateOf("") }
    val searchedPokemon by viewModel.searchedPokemon.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Input and button
        Text(text = "Search Pokémon", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Enter Pokémon Name or ID") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (searchQuery.isNotEmpty()) {
                    viewModel.searchPokemon(searchQuery)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Searched Pokémon info
        searchedPokemon?.let { pokemon ->
            Text(text = "Name: ${pokemon.name}", style = MaterialTheme.typography.headlineSmall)
            Text(text = "ID: ${pokemon.id}")
            Text(text = "Type: ${pokemon.types.joinToString { it.type.typeName }}")

            Spacer(modifier = Modifier.height(16.dp))

            // Pokémon Image
            Image(
                painter = rememberImagePainter(pokemon.imageUrl),
                contentDescription = "Pokémon Image",
                modifier = Modifier.size(200.dp)
            )
        }
    }
}
