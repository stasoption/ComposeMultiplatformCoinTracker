package ui.screens.list.components

import DownPercentIndicator
import UpPercentIndicator
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.model.Coin

@Composable
fun CoinListItem(
    coin: Coin,
    onItemClick: (Coin) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(coin) }
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                verticalAlignment = CenterVertically
            ) {
                println("$coin")

                Text(
                    text = coin.name,
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = " (${coin.symbol})",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = if(coin.isActive) "active" else "inactive",
                    color = if(coin.isActive) Color.Green else Color.Red,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.align(CenterVertically)
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "type: ${coin.type}",
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    )
}