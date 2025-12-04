package com.example.gamestore.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamestore.R
import com.example.gamestore.model.PurchasableItem

/**
 * Modal Bottom Sheet que exibe detalhes completos do item
 * permite ao utilizador efetuar a compra em si
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PurchaseBottomSheet(
    item: PurchasableItem,
    onDismiss: () -> Unit,
    onPurchase: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(bottom = 32.dp)
        ) {
            // nme do item
            Text(
                text = item.name,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            // imagem e descrição
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = item.imageResourceId),
                    contentDescription = "Imagem de ${item.name}",
                    modifier = Modifier.size(120.dp)
                )
                
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.weight(1f)
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // preço e botão de compra
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.getFormattedPrice(),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                
                Button(
                    onClick = onPurchase,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(text = "Buy with 1-click")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PurchaseBottomSheetPreview() {
    MaterialTheme {
        PurchaseBottomSheet(
            item = PurchasableItem(
                id = 1,
                name = "Item name",
                description = "Item description in brief detail. At least enough to cover 2 lines, but here it should be a bit bigger, where in the previous screen it was truncated.",
                price = 12.99,
                imageResourceId = android.R.drawable.ic_menu_gallery
            ),
            onDismiss = {},
            onPurchase = {}
        )
    }
}
