package com.example.rickandmortykt

import android.app.Application
import com.example.rickandmortykt.servicelocatior.appDatabaseModule
import com.example.rickandmortykt.servicelocatior.networkModule
import com.example.rickandmortykt.servicelocatior.repositoriesModule
import com.example.rickandmortykt.servicelocatior.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(networkModule, repositoriesModule, viewModelsModule, appDatabaseModule)
        }
    }
}