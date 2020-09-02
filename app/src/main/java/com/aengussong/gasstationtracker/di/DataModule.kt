package com.aengussong.gasstationtracker.di

import com.aengussong.gasstationtracker.repo.RepoImpl
import com.aengussong.gasstationtracker.repo.Repository
import com.aengussong.gasstationtracker.repo.local.FirebaseDataProvider
import com.aengussong.gasstationtracker.repo.local.LocalDataProvider
import com.aengussong.gasstationtracker.repo.local.UserIdProvider
import com.aengussong.gasstationtracker.repo.local.db.FirebaseDb
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    single<LocalDataProvider> { FirebaseDataProvider(get()) }

    single<Repository> { RepoImpl(get()) }

    single {
        val userId = UserIdProvider.getId(androidApplication())
        FirebaseDb.getInstance(userId).apply {
            keepSynced(true)
        }
    }
}