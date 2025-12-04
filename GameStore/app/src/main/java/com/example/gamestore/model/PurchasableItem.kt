package com.example.gamestore.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * modelo que representa um item comprável (DLC, expansão, etc.)
 * implementa "Parcelable" para poder ser passado entre Activities
 */
@Parcelize
data class PurchasableItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageResourceId: Int
) : Parcelable {
    
    /**
     * formatação do preço para a exibição
     */
    fun getFormattedPrice(): String {
        return "$%.2f".format(price)
    }
}
