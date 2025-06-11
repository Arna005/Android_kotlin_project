package com.pgr208.mypokemon.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pgr208.mypokemon.viewmodel.PokemonViewModel
import com.pgr208.mypokemon.data.model.Pokemon
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.navigation.NavHostController

@Composable
fun CreatePokemonScreen(viewModel: PokemonViewModel, navController: NavHostController) {
    var name by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    val pokemons by viewModel.allPokemons.collectAsState()
    var showSuccessMessage by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        // Input - create new Pokémon
        Text(text = "Create a New Pokémon", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Pokémon Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = type,
            onValueChange = { type = it },
            label = { Text("Pokémon Type") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Save button
        Button(
            onClick = {
                if (name.isNotEmpty() && type.isNotEmpty()) {
                    viewModel.insertPokemon(Pokemon(name = name, type = type))
                    name = ""
                    type = ""
                    showSuccessMessage = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Pokémon")
        }

        // Success message
        if (showSuccessMessage) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Pokémon created successfully!",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // List of saved Pokémon
        Text(text = "Saved Pokémon:", style = MaterialTheme.typography.headlineSmall)
        LazyColumn {
            items(pokemons) { pokemon ->
                Text(text = "${pokemon.name} - ${pokemon.type}")
            }
        }
    }
}