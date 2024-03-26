
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState

fun mainWindow() = application {
    Window(
        onCloseRequest = { applicationScope.exitApplication() },
        title = "CoinTracker",
        state = rememberWindowState(
            position = WindowPosition.Aligned(Alignment.Center),
            width = 1080.dp,
            height = 800.dp,
            )
    ) {
        App()
    }
}