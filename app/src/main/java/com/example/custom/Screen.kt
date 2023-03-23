package com.example.custom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val id: String,
    val title: String,
    val icon: ImageVector
) {
    object Camera : Screen("camera", "Camera", Icons.Outlined.Camera)
    object Galerie : Screen("galerie", "Galerie", Icons.Outlined.Image)
    object Carte : Screen("carte", "Carte", Icons.Outlined.Mail)

    object Items {
        val list = listOf(
            Camera,
            Galerie,
            Carte
        )
    }
}
