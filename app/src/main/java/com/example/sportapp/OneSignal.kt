package com.example.sportapp

import android.app.Application
import com.onesignal.OneSignal


const val ONESIGNAL_APP_ID = "cfa226b0-0b4d-4ce1-91de-88fe16ade953"

class OneSignalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }
}