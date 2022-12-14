package com.tommyappdevs.albertsonsapp.model.repository

import kotlinx.coroutines.flow.Flow

interface AcronymRepository {
    suspend fun getAcronm(acronymWord: String): Flow<UIState>
}