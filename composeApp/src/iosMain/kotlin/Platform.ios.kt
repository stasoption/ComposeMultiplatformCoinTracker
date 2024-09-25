import platform.Foundation.NSUUID

actual class Platform {
    actual val randomUUID: String
        get() = NSUUID().UUIDString
}