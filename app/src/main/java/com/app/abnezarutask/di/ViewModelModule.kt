package com.app.abnezarutask.di

import com.app.abnezarutask.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{
        MainViewModel(matchesRepository = get())
    }
}