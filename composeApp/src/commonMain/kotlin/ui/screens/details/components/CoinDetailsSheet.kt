import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import cointracker.composeapp.generated.resources.Res
import cointracker.composeapp.generated.resources.compose_multiplatform
import cointracker.composeapp.generated.resources.description
import cointracker.composeapp.generated.resources.tags
import domain.model.CoinDetail
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ui.screens.details.components.CoinTagItem
import ui.theme.TextPrimary
import ui.theme.TextSecondary

@OptIn(ExperimentalResourceApi::class, ExperimentalLayoutApi::class)
@Composable
fun CoinDetailsSheet(coin: CoinDetail) {
    Column(
        Modifier.fillMaxWidth()
    ) {
        /* Title area */
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(Res.drawable.compose_multiplatform),
                contentDescription = "Empty state image",
                modifier = Modifier.width(Logotype).height(Logotype)
            )
            Spacer(modifier = Modifier.width(SmallPadding))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = buildAnnotatedString {
                    append(coin.name)
                    pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                    append(" (${coin.symbol})")
                    pop()
                },
                color = TextPrimary,
                style = MaterialTheme.typography.h1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(modifier = Modifier.height(BigPadding))

        /* Description area */
        Text(
            text = stringResource(Res.string.description),
            color = TextPrimary,
            style = MaterialTheme.typography.h3
        )
        Spacer(modifier = Modifier.height(SmallPadding))
        Text(
            text = coin.description,
            color = TextSecondary,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(MediumPadding))

        /* Tags area */
        Text(
            text = stringResource(Res.string.tags),
            color = TextPrimary,
            style = MaterialTheme.typography.h3
        )
        Spacer(modifier = Modifier.height(SmallPadding))
        FlowRow(modifier = Modifier.fillMaxWidth()) {
            coin.tags.forEach { tag -> CoinTagItem(tag = tag) }
        }
    }
}