package com.tommyappdevs.albertsonsapp.model.repository

import com.tommyappdevs.albertsonsapp.model.AcronymsResponseItem

sealed class UIState{
    data class Success(val data: List<AcronymsResponseItem>): UIState()
    data class Failure(val errorMessage: String): UIState()
    data class Loading(val loading: Boolean): UIState()
    object Empty: UIState()
}
