package com.transforma.app

import KoinInitializer
import android.app.Application

class TransformaApp : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinInitializer(applicationContext).init()
    }
}