package com.deepu.myandroidapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.deepu.myandroidapp.ui.LocalSpacing
import com.deepu.myandroidapp.ui.Spacing

private val DarkColorPalette = darkColors(
    primary = DarkByzantineBlue,
    primaryVariant = DarkByzantineBlue,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = DarkByzantineBlue,
    primaryVariant = DarkByzantineBlue,
    secondary = DarkByzantineBlue

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun MyAndroidAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    //using local provider to provide the spacing composition  local that we've created
    CompositionLocalProvider(LocalSpacing provides Spacing() /*can add multiple local providers here by adding commas*/) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content,
        )
        
    }


}