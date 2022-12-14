package com.tommyappdevs.albertsonsapp.di

import com.tommyappdevs.albertsonsapp.model.newtork.AcronymService
import com.tommyappdevs.albertsonsapp.model.repository.AcronymRepository
import com.tommyappdevs.albertsonsapp.model.repository.AcronymRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ModuleRepository {
    @Provides
    fun provideRepository(service: AcronymService): AcronymRepository = AcronymRepositoryImpl(service)
}