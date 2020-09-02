package com.aengussong.gasstationtracker.app

import android.app.Application
import com.aengussong.gasstationtracker.di.dataModule
import com.aengussong.gasstationtracker.di.viewModelModule
import com.google.firebase.database.FirebaseDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StationApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseDatabase.getInstance().setPersistenceEnabled(true)

        startKoin {
            androidContext(this@StationApplication)
            modules(viewModelModule, dataModule)
        }
    }
}