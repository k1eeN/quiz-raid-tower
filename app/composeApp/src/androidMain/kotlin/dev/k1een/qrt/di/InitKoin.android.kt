package dev.k1een.qrt.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

fun initAndroidKoin(application: Application) {
    initKoin {
        androidContext(application)
        androidLogger()
    }
}
