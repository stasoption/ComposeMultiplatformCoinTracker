import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailsPlaceholder() {

    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val shimmerColors = listOf(
        Color.LightGray.copy(0.9f),
        Color.LightGray.copy(0.2f),
        Color.LightGray.copy(0.9f)
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )

    Column(
        Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = CenterVertically
        ) {
            /* Title area */
            Spacer(
                modifier = Modifier
                    .width(Logotype)
                    .height(Logotype)
                    .background(brush = brush, shape = RoundedCornerShape(4.dp))
            )
            Spacer(modifier = Modifier.width(SmallPadding))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Logotype)
                    .background(brush = brush, shape = RoundedCornerShape(4.dp))
            )
        }
        Spacer(modifier = Modifier.height(BigPadding))

        /* Description area */
        Spacer(
            modifier = Modifier
                .width(100.dp)
                .height(24.dp)
                .background(brush = brush, shape = RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.height(SmallPadding))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(brush = brush, shape = RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.height(MediumPadding))

        /* Tags area */
        Spacer(
            modifier = Modifier
                .width(100.dp)
                .height(24.dp)
                .background(brush = brush, shape = RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.height(SmallPadding))
        FlowRow(modifier = Modifier.fillMaxWidth()) {
            for (i in 1..10) {
                Spacer(
                    modifier = Modifier
                        .padding(end = TinyPadding, bottom = TinyPadding)
                        .width(if (i % 2 == 0) 60.dp else 40.dp)
                        .height(24.dp)
                        .background(brush = brush, shape = RoundedCornerShape(4.dp))
                )
            }
        }
    }
}