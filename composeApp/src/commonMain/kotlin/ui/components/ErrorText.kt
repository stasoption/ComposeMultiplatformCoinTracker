import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ui.theme.TextPrimary


@Composable
fun ErrorText(
    text: String?,
    color: Color = TextPrimary,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    Box(modifier = modifier) {
        val error = rememberSaveable { mutableStateOf(text) }
        AnimatedVisibility(
            visible = text.isNullOrBlank().not(),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = error.value.orEmpty(),
                color = color,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .align(Alignment.Center)
            )
        }
    }
}