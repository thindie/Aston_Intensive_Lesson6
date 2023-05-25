package com.example.thindie.aston_intensive_lesson5.application

import android.app.Application
import com.example.thindie.aston_intensive_lesson5.Router
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ContactsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(Router.Companion.ActivityLifeCycleGetter())

    }
}
