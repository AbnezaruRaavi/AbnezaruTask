package com.app.abnezarutask.di

import com.app.abnezarutask.data.repositories.MatchesRepository
import com.app.abnezarutask.data.repositoriesImpl.MatchesRepositoryImpl
import org.koin.dsl.module

val repoModule = module {
    single<MatchesRepository> {
        MatchesRepositoryImpl(get(), get())
    }
}