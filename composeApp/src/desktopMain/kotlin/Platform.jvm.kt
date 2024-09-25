import java.util.UUID
import java.awt.Toolkit

actual class Platform {
    actual val randomUUID: String = "${UUID.randomUUID()}"
    actual val screenWidth: Int
        get() {
            val toolkit = Toolkit.getDefaultToolkit()
            val screenSize = toolkit.screenSize
            return screenSize.width
        }
}
