import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class UpPercentIndicator : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
       val trianglePath = Path().apply {
                moveTo(size.width / 2f, 0f)
                lineTo(size.width, size.width)
                lineTo(0f, size.width)
            }
        return Outline.Generic(path = trianglePath)
    }
}
class DownPercentIndicator : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val trianglePath = Path().apply {
            moveTo(size.width / 2f, size.width)
                lineTo(0F, 0f)
            lineTo(size.width, 0f)
            }
        return Outline.Generic(path = trianglePath)
    }
}