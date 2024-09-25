import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.runtime.Composable
import java.util.UUID

actual class Platform {
    actual val randomUUID: String
        get() = UUID.randomUUID().toString()
    actual val screenWidth: Int
        @Composable
        get() = LocalConfiguration.current.screenWidthDp
}
