package com.pgr208.mypokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.pgr208.mypokemon.ui.screens.CreatePokemonScreen
import com.pgr208.mypokemon.ui.screens.ShowPokemonScreen
import com.pgr208.mypokemon.ui.screens.SearchPokemonScreen
import com.pgr208.mypokemon.viewmodel.PokemonViewModel
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search

class MainActivity : ComponentActivity() {
    private val pokemonViewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyPokemonApp(pokemonViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPokemonApp(viewModel: PokemonViewModel) {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    // Scaffold, TopBar, BottomNavigation, and NavHost
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Pokémon", color = MaterialTheme.colorScheme.onPrimary) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {

                // BottomNavigationItems for Create, Saved, and Search screens
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Add, contentDescription = "Create") },
                    label = { Text("Create") },
                    selected = currentRoute == "createPokemon",
                    onClick = { navController.navigate("createPokemon") }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.List, contentDescription = "Saved") },
                    label = { Text("Saved") },
                    selected = currentRoute == "showPokemon",
                    onClick = { navController.navigate("showPokemon") }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                    label = { Text("Search") },
                    selected = currentRoute == "searchPokemon",
                    onClick = { navController.navigate("searchPokemon") }
                )
            }
        }
    ) { padding ->

        // Navigation between screens
        NavHost(
            navController = navController,
            startDestination = "createPokemon",
            modifier = Modifier.padding(padding)
        ) {
            composable("createPokemon") { CreatePokemonScreen(viewModel, navController) }
            composable("showPokemon") { ShowPokemonScreen(viewModel) }
            composable("searchPokemon") { SearchPokemonScreen(viewModel) }
        }
    }
}