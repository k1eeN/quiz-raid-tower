package dev.k1een.qrt

import android.app.Application
import dev.k1een.qrt.di.initAndroidKoin

class QrtApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initAndroidKoin(this)
    }
}
