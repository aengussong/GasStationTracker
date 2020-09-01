package com.aengussong.gasstationtracker.di

import com.aengussong.gasstationtracker.ui.addstation.AddStationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { AddStationViewModel() }
}