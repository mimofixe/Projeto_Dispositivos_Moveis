package com.example.gamestore

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamestore.data.GameRepository
import com.example.gamestore.model.Game
import com.example.gamestore.ui.components.GameCard
import com.example.gamestore.ui.theme.GameStoreTheme

/**
 * activity principal que exibe a lista de jogos disponíveis
 * permite navegação para os detalhes de cada jogo
 */
class MainActivity : ComponentActivity() {
    
    // lista de jogos carregada do repositório
    private lateinit var games: List<Game>
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // carregar jogos do repositório (simula carregamento de API)
        games = GameRepository.getGames()
        
        setContent {
            GameStoreTheme {
                MainScreen(
                    games = games,
                    onGameClick = { game ->
                        navigateToGameDetail(game)
                    }
                )
            }
        }
    }
    
    /**
     * navega para a tela de detalhes do jogo
     * vai passar o objeto Game via Intent
     */
    private fun navigateToGameDetail(game: Game) {
        val intent = Intent(this, GameDetailActivity::class.java).apply {
            putExtra("GAME_DATA", game)
        }
        startActivity(intent)
    }
}

/**
 * tela principal com TopBar, lista de jogos e BottomNavigationBar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    games: List<Game>,
    onGameClick: (Game) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(1) }
    
    Scaffold(
        topBar = {
            MainTopBar()
        },
        bottomBar = {
            MainBottomNavigationBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        },
        modifier = modifier
    ) { paddingValues ->
        // Lista de jogos
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(games) { game ->
                GameCard(
                    game = game,
                    onClick = { onGameClick(game) }
                )
            }
        }
    }
}

/**
 * barra superior com nome da empresa e ícones de notificações e configurações
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Empresa Super Fixe",
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            IconButton(onClick = { /* Ação de notificações */ }) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notificações"
                )
            }
            IconButton(onClick = { /* Ação de configurações */ }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Configurações"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

/**
 * Barra de navegação inferior com três tabs
 */
@Composable
fun MainBottomNavigationBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Featured"
                )
            },
            label = { Text("Featured") },
            selected = selectedTab == 0,
            onClick = { onTabSelected(0) }
        )
        
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "History"
                )
            },
            label = { Text("History") },
            selected = selectedTab == 1,
            onClick = { onTabSelected(1) }
        )
        
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile"
                )
            },
            label = { Text("Profile") },
            selected = selectedTab == 2,
            onClick = { onTabSelected(2) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    GameStoreTheme {
        MainScreen(
            games = GameRepository.getGames(),
            onGameClick = {}
        )
    }
}
