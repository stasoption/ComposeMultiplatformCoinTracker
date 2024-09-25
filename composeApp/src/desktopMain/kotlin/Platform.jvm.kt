import java.util.UUID

actual class Platform {
    actual val randomUUID: String = "${UUID.randomUUID()}"
}
