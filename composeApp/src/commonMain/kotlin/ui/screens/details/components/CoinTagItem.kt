package ui.screens.details.components

import MediumRadius
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import SmallPadding
import TinyPadding
import data.sources.dto.Tag
import ui.theme.ColorAccent
import ui.theme.MediumGray
import ui.theme.TextPrimary

@Composable
fun CoinTagItem(
    tag: Tag
) {
    Box(
        modifier = Modifier
            .padding(end = TinyPadding, bottom = TinyPadding)
            .border(
                width = 0.5.dp,
                color = ColorAccent,
                shape = RoundedCornerShape(MediumRadius)
            )
            .background(color = MediumGray, shape = RoundedCornerShape(MediumRadius))
            .padding(SmallPadding)
    ) {
        Text(
            text = tag.name.orEmpty(),
            color = TextPrimary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2
        )
    }
}