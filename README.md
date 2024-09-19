I'm currently working on a Compose Multiplatform pet project that aims to research Kotlin's modern UI framework
for building native user interfaces across Android, iOS, and (maybe later) desktop platforms.
This project helps me explore the potential of code sharing between platforms and optimize development workflows for mobile and desktop apps.

Feel free to fork and tweak it based on your specific goals

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.