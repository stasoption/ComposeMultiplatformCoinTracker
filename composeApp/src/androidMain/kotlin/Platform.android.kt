import java.util.UUID

actual class Platform {
    actual val randomUUID: String
        get() = UUID.randomUUID().toString()
}
