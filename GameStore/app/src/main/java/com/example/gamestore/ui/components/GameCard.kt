package com.example.gamestore.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamestore.R
import com.example.gamestore.model.Game

/**
 * exibe imagem de fundo e o nome do jogo
 */
@Composable
fun GameCard(
    game: Game,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // imagem de fundo do jogo
            Image(
                painter = painterResource(id = game.backgroundImageResourceId),
                contentDescription = "Imagem de fundo de ${game.name}",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            
            // nome do jogo sobreposto
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // icone pequeno do jogo
                Image(
                    painter = painterResource(id = game.imageResourceId),
                    contentDescription = "Ícone de ${game.name}",
                    modifier = Modifier
                        .size(48.dp)
                        .padding(end = 12.dp)
                )
                
                Text(
                    text = game.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameCardPreview() {
    MaterialTheme {
        GameCard(
            game = Game(
                id = 1,
                name = "Nome do Jogo",
                description = "Descrição de exemplo",
                imageResourceId = android.R.drawable.ic_menu_gallery,
                backgroundImageResourceId = android.R.drawable.ic_menu_slideshow,
                purchasableItems = emptyList()
            ),
            onClick = {}
        )
    }
}
