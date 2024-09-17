package ui.screens.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.theme.DarkGray
import ui.theme.TextError
import ui.theme.TextPrimary
import ui.theme.TextSecondary
import ui.theme.TextSuccess

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
                text = "New ${coin.type}: ",
                color = TextSecondary,
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Normal
            )

            Text(
                text = if (coin.isNew) "Yes" else "No",
                color = TextPrimary,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Status: ",
                color = TextSecondary,
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Normal
            )

            Text(
                text = if (coin.isActive) "Active" else "Inactive",
                color = if (coin.isActive) TextSuccess else TextError,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold
            )
        }
    }

}

@Preview
@Composable
fun CoinListItemPreview() {
    CoinListItem(
        Coin("1", true, true, "Bitcoin", 1, "BTC", "Crypto" )
    ) {}
}