package com.example.cofeebreak

import android.app.Application
import androidx.compose.ui.platform.LocalContext
import com.example.cofeebreak.di.moduleAuth
import com.example.cofeebreak.di.moduleCurrentSession
import com.example.cofeebreak.di.moduleProfile
import com.example.cofeebreak.di.moduleVM
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            androidLogger(level = Level.DEBUG)
            modules(
                moduleVM, moduleAuth, moduleProfile, moduleCurrentSession
            )
        }
    }
}