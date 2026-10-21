package dev.k1een.qrt

import android.app.Application
import dev.k1een.qrt.core.designsystem.components.preloadTowerAnimation
import dev.k1een.qrt.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

fun initAndroidApp(application: Application) {
    preloadTowerAnimation(application)
    initKoin {
        androidContext(application)
        androidLogger()
    }
}
