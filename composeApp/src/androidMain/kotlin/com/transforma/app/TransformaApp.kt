package com.transforma.app

import android.app.Application
import di.initializeKoin

class TransformaApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }
}