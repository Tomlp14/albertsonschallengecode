package com.tommyappdevs.albertsonsapp.model.repository

import com.tommyappdevs.albertsonsapp.model.newtork.AcronymService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AcronymRepositoryImpl(private val service: AcronymService) : AcronymRepository {


    override suspend fun getAcronm(acronymWord: String): Flow<UIState> {
        return flow {
            emit(UIState.Loading(true))
            val response = service.getAcronyms(acronymWord)

            if (response.isSuccessful){
                response.body()?.let{
                    emit(
                        UIState.Success(it)
                    )
                } ?: emit(
                    UIState.Failure(response.message())
                )
            } else{
                UIState.Failure(response.message())
            }
            emit(UIState.Loading(false))
        }
    }

}
