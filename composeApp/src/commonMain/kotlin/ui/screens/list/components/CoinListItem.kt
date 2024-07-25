package ui.screens.list.components

import Coin
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
                Text(
                    text = "${coin.name}",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = " (${coin.symbol})",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.weight(1f))

                val indicator = if (coin.isPercent24HrUp) UpPercentIndicator() else DownPercentIndicator()
                val indicatorColor = if (coin.isPercent24HrUp) Color.Green else Color.Red
                Box(modifier = Modifier.size(16.dp).clip(shape = indicator).background(indicatorColor))

                Text(
                    text = " ${coin.formattedPercent}%",
                    fontSize = 14.sp,
                    color = indicatorColor,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "$${coin.formattedPrice}",
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    )
}