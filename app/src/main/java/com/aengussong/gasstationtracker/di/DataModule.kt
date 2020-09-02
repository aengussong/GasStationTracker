package com.aengussong.gasstationtracker.di

import androidx.room.Room
import com.aengussong.gasstationtracker.repo.RepoImpl
import com.aengussong.gasstationtracker.repo.Repository
import com.aengussong.gasstationtracker.repo.local.LocalDataProvider
import com.aengussong.gasstationtracker.repo.local.RoomLocalProvider
import com.aengussong.gasstationtracker.repo.local.db.GasStationDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    single<LocalDataProvider> { RoomLocalProvider(get()) }

    single<Repository> { RepoImpl(get()) }

    single {
        Room.databaseBuilder(
            androidApplication(),
            GasStationDatabase::class.java,
            "GasStationDatabase"
        ).build()
    }

    single { get<GasStationDatabase>().stationDao() }
}