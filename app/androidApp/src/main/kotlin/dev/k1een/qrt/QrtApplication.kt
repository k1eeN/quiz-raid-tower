package dev.k1een.qrt

import android.app.Application

class QrtApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initAndroidApp(this)
    }
}
