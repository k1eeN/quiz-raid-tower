package dev.k1een.qrt.feature.game.api.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object SetupRaid : NavKey

@Serializable
data class Battle(
    val categoryId: Int? = null,
    val difficulty: String = "medium",
) : NavKey

@Serializable
data object FloorCompleted : NavKey

@Serializable
data object RaidResult : NavKey
