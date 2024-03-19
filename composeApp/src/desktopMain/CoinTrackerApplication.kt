package com.solitude.cointracker

import androidx.compose.ui.window.application
import com.vickbt.notflix.ui.windows.MainWindow
import com.vickbt.shared.di.initKoin
import org.koin.core.Koin

lateinit var koin: Koin

fun main() {
    koin = initKoin(enableNetworkLogs = true).koin

    return application {
        MainWindow(applicationScope = this)
    }
}