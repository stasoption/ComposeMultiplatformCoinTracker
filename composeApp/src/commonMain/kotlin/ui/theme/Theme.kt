package ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import ui.components.Shapes

private val LightColorPalette = lightColors(
    primary = DarkGray,
    secondary = ColorAccent,
    background = MediumGray,
)

@Composable
fun CoinTrackerAppTheme(darkTheme: Boolean = true, content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = LightColorPalette, // <-- TODO: Implement Dark theme
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}