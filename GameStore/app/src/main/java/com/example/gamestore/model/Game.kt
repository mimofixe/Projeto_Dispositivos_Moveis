package com.example.gamestore.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * modelo que representa um jogo na loja
 * contém informações do jogo e lista de itens compráveis
 */
@Parcelize
data class Game(
    val id: Int,
    val name: String,
    val description: String,
    val imageResourceId: Int,
    val backgroundImageResourceId: Int,
    val purchasableItems: List<PurchasableItem>
) : Parcelable
