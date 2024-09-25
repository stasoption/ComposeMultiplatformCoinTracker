import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo
import platform.Foundation.NSUUID

actual class Platform {
    actual val randomUUID: String
        get() = NSUUID().UUIDString
    @OptIn(ExperimentalComposeUiApi::class)
    actual val screenWidth: Int
        @Composable
        get() = LocalWindowInfo.current.containerSize.width
}