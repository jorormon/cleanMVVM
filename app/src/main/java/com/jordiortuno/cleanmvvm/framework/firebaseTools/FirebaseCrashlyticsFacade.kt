package com.jordiortuno.cleanmvvm.framework.firebaseTools

import android.content.Context
import com.jordiortuno.cleanmvvm.framework.MyApp
import com.jordiortuno.cleanmvvm.framework.MyApp.Companion.appStatus
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics

object FirebaseCrashlyticsFacade {

    private val instance = FirebaseCrashlytics.getInstance()

    fun init(context:Context){
        FirebaseApp.initializeApp(context)
        initCrashlytics()
        sendUnsentReports()
    }
    private fun initCrashlytics() {
        if (appStatus === MyApp.Environment.PRODUCTION || appStatus === MyApp.Environment.TEST) {
            instance.setCrashlyticsCollectionEnabled(true)
        }
    }

    private fun sendUnsentReports() {
        if (appStatus === MyApp.Environment.PRODUCTION) {
            instance.sendUnsentReports()
        } else {
            instance.deleteUnsentReports()
        }
    }
}