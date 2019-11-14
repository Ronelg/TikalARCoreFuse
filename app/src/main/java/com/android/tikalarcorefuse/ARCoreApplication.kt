package com.android.tikalarcorefuse

import android.app.Application
import timber.log.Timber

class ARCoreApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}