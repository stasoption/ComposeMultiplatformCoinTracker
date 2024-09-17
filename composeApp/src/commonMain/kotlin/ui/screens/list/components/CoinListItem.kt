package ui.screens.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import domain.model.Coin
import ui.theme.DarkGray
import ui.theme.TextPrimary
import ui.theme.TextSecondary

@Composable
fun CoinListItem(
    coin: Coin,
    onItemClick: (Coin) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(coin) }
            .background(DarkGray)
            .padding(top = 16.dp, bottom = 16.dp),
        verticalAlignment = CenterVertically
    ) {
        println("$coin")

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
}