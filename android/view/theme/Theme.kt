package com.tobiasheymann.ep.view.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = DARK_PRIMARY,
    primaryVariant = DARK_PRIMARY_VARIABLE,
    secondary = DARK_SECONDARY,
    secondaryVariant = DARK_SECONDARY_VARIABLE,
    surface = DARK_SURFACE,
    background = DARK_BACKGROUND,
    onPrimary = DARK_TEXT_PRIMARY,
    onSecondary = DARK_TEXT_SECONDARY
)

private val LightColorPalette = lightColors(
    primary = PRIMARY,
    primaryVariant = PRIMARY_VARIABLE,
    secondary = SECONDARY,
    secondaryVariant = SECONDARY_VARIABLE,
    surface = SURFACE,
    background = BACKGROUND,
    onPrimary = TEXT_PRIMARY,
    onSecondary = TEXT_SECONDARY
)

@Composable
fun EPTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val systemUiController = rememberSystemUiController()
    val colors: Colors
    val typography: Typography

    if (darkTheme) {
        colors = DarkColorPalette
        typography = DarkTypography
        systemUiController.setSystemBarsColor(
            color = DarkColorPalette.background,
            darkIcons = false
        )
    } else {
        colors = LightColorPalette
        typography = LightTypography
        systemUiController.setSystemBarsColor(
            color = LightColorPalette.background,
            darkIcons = true
        )
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = Shapes,
        content = content
    )
}