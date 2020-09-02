package com.aengussong.gasstationtracker.di

import com.aengussong.gasstationtracker.ui.addstation.AddStationViewModel
import com.aengussong.gasstationtracker.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { AddStationViewModel(get()) }
    viewModel { MainViewModel(get()) }
}