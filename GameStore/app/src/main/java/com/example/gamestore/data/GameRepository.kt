package com.example.gamestore.data

import com.example.gamestore.R
import com.example.gamestore.model.Game
import com.example.gamestore.model.PurchasableItem

/**
 * repositório que simula dados vindos de um serviço web
 */
object GameRepository {

    /**
     * este método faria um pedido à API
     */
    fun getGames(): List<Game> {
        return listOf(
            createCyberpunk2077Game(),
            createTheWitcher3Game()
        )
    }

    /**
     * cria cyberpunk
     */
    private fun createCyberpunk2077Game(): Game {
        val items = listOf(
            PurchasableItem(
                id = 1,
                name = "Phantom Liberty",
                description = "Expansão épica que adiciona uma nova área de Night City com missões de espionagem. Inclui novas armas, cyberware e um final alternativo para a história principal.",
                price = 29.99,
                imageResourceId = R.drawable.cyberpunk_dlc1
            ),
            PurchasableItem(
                id = 2,
                name = "Edgerunners Pack",
                description = "Pacote de itens inspirado na série animada. Contém roupa icónica da Rebecca, armas personalizadas e veículo exclusivo da série.",
                price = 12.99,
                imageResourceId = R.drawable.cyberpunk_dlc2
            ),
            PurchasableItem(
                id = 3,
                name = "Ultimate Weapon Bundle",
                description = "Coleção definitiva de armamento para mercenários. Inclui 15 armas únicas, skins exclusivas e modificações especiais para combate.",
                price = 19.99,
                imageResourceId = R.drawable.cyberpunk_dlc3
            )
        )

        return Game(
            id = 1,
            name = "Cyberpunk 2077",
            description = "Jogo de RPG em mundo aberto passado em Night City, uma megalópole obcecada por poder, glamour e modificações corporais. Encarnas V, um mercenário fora da lei em busca de um implante único que é a chave para a imortalidade.",
            imageResourceId = R.drawable.cyberpunk_icon,
            backgroundImageResourceId = R.drawable.cyberpunk_bg,
            purchasableItems = items
        )
    }

    /**
     * cria witcher 3
     */
    private fun createTheWitcher3Game(): Game {
        val items = listOf(
            PurchasableItem(
                id = 4,
                name = "Blood and Wine",
                description = "Expansão massiva que adiciona mais de 30 horas de conteúdo. Explora Toussaint, uma terra intocada pela guerra, resolve mistérios sombrios e enfrenta novos monstros.",
                price = 19.99,
                imageResourceId = R.drawable.witcher_dlc1
            ),
            PurchasableItem(
                id = 5,
                name = "Hearts of Stone",
                description = "Primeira grande expansão com 10+ horas de história. Uma aventura sombria que introduz o misterioso Homem de Espelhos e missões com escolhas morais difíceis.",
                price = 9.99,
                imageResourceId = R.drawable.witcher_dlc2
            ),
            PurchasableItem(
                id = 6,
                name = "Witcher Gear Collection",
                description = "Conjunto completo de equipamentos de bruxo. Inclui armaduras das escolas do Gato, Urso, Grifo e Lobo, cada uma com bónus únicos e visual impressionante.",
                price = 14.99,
                imageResourceId = R.drawable.witcher_dlc3
            )
        )

        return Game(
            id = 2,
            name = "The Witcher 3: Wild Hunt",
            description = "RPG de ação em mundo aberto, visualmente deslumbrante. És Geralt de Rívia, caçador de monstros profissional, numa missão para encontrar a criança da profecia num vasto mundo repleto de cidades mercantes, ilhas vikings e cavernas perigosas.",
            imageResourceId = R.drawable.witcher_icon,
            backgroundImageResourceId = R.drawable.witcher_bg,
            purchasableItems = items
        )
    }
}