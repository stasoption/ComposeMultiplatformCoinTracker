package ui.screens.list.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cointracker.composeapp.generated.resources.Res
import cointracker.composeapp.generated.resources.active
import cointracker.composeapp.generated.resources.inactive
import cointracker.composeapp.generated.resources.new
import cointracker.composeapp.generated.resources.no
import cointracker.composeapp.generated.resources.status
import cointracker.composeapp.generated.resources.yes
import domain.model.Coin
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import ui.theme.DarkGray
import ui.theme.TextError
import ui.theme.TextPrimary
import ui.theme.TextSecondary
import ui.theme.TextSuccess

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CoinListItem(
    coin: Coin,
    onItemClick: (Coin) -> Unit
) {
    Column(
        Modifier.fillMaxWidth()
            .clickable { onItemClick(coin) }
            .background(DarkGray)
            .padding(top = 16.dp, bottom = 16.dp)
            .height(IntrinsicSize.Min)
    ) {
        Row(verticalAlignment = CenterVertically) {
            Text(
                text = coin.name,
                color = TextPrimary,
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = coin.symbol,
                color = TextSecondary,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(start = 8.dp).weight(1f)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Type: ${coin.type}",
                color = TextPrimary,
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Normal
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = CenterVertically) {

            Text(
                text = "${stringResource(Res.string.new)} ${coin.type}: ",
                color = TextSecondary,
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Normal
            )

            Text(
                text = if (coin.isNew) stringResource(Res.string.yes) else stringResource(Res.string.no),
                color = TextPrimary,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "${stringResource(Res.string.status)}: ",
                color = TextSecondary,
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Normal
            )

            Text(
                text = if (coin.isActive) stringResource(Res.string.active) else stringResource(Res.string.inactive),
                color = if (coin.isActive) TextSuccess else TextError,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold
            )
        }
    }

}

@Composable
fun CoinShimmerItem() {

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
            .background(DarkGray)
            .padding(top = 16.dp, bottom = 16.dp)
            .height(IntrinsicSize.Min)
    ) {
        Row(verticalAlignment = CenterVertically) {

            // Name
            Spacer(
                modifier = Modifier
                    .width(50.dp)
                    .height(16.dp)
                    .background(brush = brush, shape = RoundedCornerShape(4.dp))
            )

            Spacer(modifier = Modifier.width(4.dp))

            // Symbol
            Spacer(
                modifier = Modifier
                    .width(30.dp)
                    .height(16.dp)
                    .background(brush = brush, shape = RoundedCornerShape(4.dp))
            )

            Spacer(modifier = Modifier.weight(1f))

            // Type title
            Spacer(
                modifier = Modifier
                    .width(50.dp)
                    .height(16.dp)
                    .background(brush = brush, shape = RoundedCornerShape(4.dp))
            )

            Spacer(modifier = Modifier.width(4.dp))

            // Type value
            Spacer(
                modifier = Modifier
                    .width(30.dp)
                    .height(16.dp)
                    .background(brush = brush, shape = RoundedCornerShape(4.dp))
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = CenterVertically) {

            // New coin title
            Spacer(
                modifier = Modifier
                    .width(30.dp)
                    .height(16.dp)
                    .background(brush = brush, shape = RoundedCornerShape(4.dp))
            )

            Spacer(modifier = Modifier.width(4.dp))

            // New coin value
            Spacer(
                modifier = Modifier
                    .width(50.dp)
                    .height(16.dp)
                    .background(brush = brush, shape = RoundedCornerShape(4.dp))
            )

            Spacer(modifier = Modifier.weight(1f))

            // Status title
            Spacer(
                modifier = Modifier
                    .width(30.dp)
                    .height(16.dp)
                    .background(brush = brush, shape = RoundedCornerShape(4.dp))
            )

            Spacer(modifier = Modifier.width(4.dp))

            // Status value
            Spacer(
                modifier = Modifier
                    .width(50.dp)
                    .height(16.dp)
                    .background(brush = brush, shape = RoundedCornerShape(4.dp))
            )
        }
    }

}
