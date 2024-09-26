import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cointracker.composeapp.generated.resources.Res
import cointracker.composeapp.generated.resources.compose_multiplatform
import cointracker.composeapp.generated.resources.empty_state_message
import cointracker.composeapp.generated.resources.empty_state_title
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ui.theme.DarkGray
import ui.theme.TextPrimary

@OptIn(ExperimentalResourceApi::class)
@Composable
fun EmptyState(
    isVisible: Boolean = false,
    title: String = stringResource(Res.string.empty_state_title),
    message: String = stringResource(Res.string.empty_state_message),
    titleColor: Color = TextPrimary,
    messageColor: Color = TextPrimary,
    modifier: Modifier = Modifier.fillMaxSize().background(DarkGray)
) {
    AnimatedVisibility(visible = isVisible) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(Res.drawable.compose_multiplatform),
                contentDescription = "Empty state image",
                modifier = Modifier.width(150.dp).height(150.dp)
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(BigPadding))
            Text(
                text = title,
                color = titleColor,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h2,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LargePadding)
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(MediumPadding))
            Text(
                text = message,
                color = messageColor,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LargePadding)
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(54.dp))
        }
    }
}