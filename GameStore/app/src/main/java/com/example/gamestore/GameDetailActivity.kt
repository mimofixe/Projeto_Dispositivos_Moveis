package com.example.gamestore

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamestore.data.GameRepository
import com.example.gamestore.model.Game
import com.example.gamestore.model.PurchasableItem
import com.example.gamestore.ui.components.PurchasableItemCard
import com.example.gamestore.ui.components.PurchaseBottomSheet
import com.example.gamestore.ui.theme.GameStoreTheme

/**
 * activity que exibe os detalhes de um jogo específico
 * e a lista de itens compráveis
 */
class GameDetailActivity : ComponentActivity() {
    
    private lateinit var game: Game
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // recuperar o jogo passado via Intent
        game = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("GAME_DATA", Game::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("GAME_DATA")
        } ?: run {
            // sse não houver jogo, voltar à activity anterior
            finish()
            return
        }
        
        setContent {
            GameStoreTheme {
                GameDetailScreen(
                    game = game,
                    onBackClick = { finish() },
                    onPurchase = { item ->
                        showPurchaseToast(item)
                    }
                )
            }
        }
    }
    
    /**
     * mostra mensagem de confirmação de compra
     */
    private fun showPurchaseToast(item: PurchasableItem) {
        val message = "Acabou de comprar o item ${item.name} por ${item.getFormattedPrice()}"
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}

/**
 * atela de detalhes do jogo com lista de itens compráveis
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailScreen(
    game: Game,
    onBackClick: () -> Unit,
    onPurchase: (PurchasableItem) -> Unit,
    modifier: Modifier = Modifier
) {
    // rstado para controlar qual dos items está selecionado para compra
    var selectedItem by remember { mutableStateOf<PurchasableItem?>(null) }
    var isFavorite by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            GameDetailTopBar(
                gameName = game.name,
                isFavorite = isFavorite,
                onBackClick = onBackClick,
                onFavoriteClick = { isFavorite = !isFavorite }
            )
        },
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // cabeçalho com imagem e descrição do jogo
            item {
                GameHeader(game = game)
            }
            
            // titulo da secção de itens compráveis
            item {
                Text(
                    text = "Purchasable Items",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            
            // lista de itens compráveis
            items(game.purchasableItems) { item ->
                PurchasableItemCard(
                    item = item,
                    onClick = { selectedItem = item }
                )
            }
        }
    }
    
    // modal Bottom Sheet para confirmar compra
    selectedItem?.let { item ->
        PurchaseBottomSheet(
            item = item,
            onDismiss = { selectedItem = null },
            onPurchase = {
                onPurchase(item)
                selectedItem = null
            }
        )
    }
}


 //* barra superior com botão de voltar, nome do jogo e favorito

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailTopBar(
    gameName: String,
    isFavorite: Boolean,
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = gameName,
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Voltar"
                )
            }
        },
        actions = {
            IconButton(onClick = onFavoriteClick) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = if (isFavorite) "Remover dos favoritos" else "Adicionar aos favoritos",
                    tint = if (isFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                )
            }
        }
    )
}

/**
 * cabeçalgo
 */
@Composable
fun GameHeader(game: Game) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // imagem do jogo
            Image(
                painter = painterResource(id = game.imageResourceId),
                contentDescription = "Imagem de ${game.name}",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )
            
            // descrição do jogo
            Text(
                text = game.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameDetailScreenPreview() {
    GameStoreTheme {
        GameDetailScreen(
            game = GameRepository.getGames().first(),
            onBackClick = {},
            onPurchase = {}
        )
    }
}
