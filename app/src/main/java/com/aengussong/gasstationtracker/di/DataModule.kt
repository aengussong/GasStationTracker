package com.aengussong.gasstationtracker.di

import com.aengussong.gasstationtracker.repo.RepoImpl
import com.aengussong.gasstationtracker.repo.Repository
import com.aengussong.gasstationtracker.repo.local.LocalDataProvider
import com.aengussong.gasstationtracker.repo.local.LocalDataProviderImpl
import org.koin.dsl.module

val dataModule = module {
    single<LocalDataProvider> { LocalDataProviderImpl() }
    single<Repository> { RepoImpl(get()) }
}