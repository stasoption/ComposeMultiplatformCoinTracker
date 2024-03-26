package com.solitude.cointracker

import androidx.compose.ui.window.application
import org.koin.core.Koin

lateinit var koin: Koin

fun main() {
    koin = initKoin(enableNetworkLogs = true).koin

    return application {
        mainWindow()
    }
}