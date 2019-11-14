package com.android.tikalarcorefuse

import android.app.Application
import timber.log.Timber

class ARCoreApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}