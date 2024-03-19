package com.solitude.cointracker

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import di.initKoin
import di.utils.ContextUtils

class CoinTrackerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        ContextUtils.setContext(context = this)

        initKoin {
            androidLogger(level = Level.NONE)
            androidContext(androidContext = this@CoinTrackerApplication)
        }
    }
}
