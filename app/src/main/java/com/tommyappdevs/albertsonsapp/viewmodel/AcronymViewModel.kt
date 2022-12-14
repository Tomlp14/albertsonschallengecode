package com.tommyappdevs.albertsonsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tommyappdevs.albertsonsapp.model.repository.AcronymRepository
import com.tommyappdevs.albertsonsapp.model.repository.AcronymRepositoryImpl
import com.tommyappdevs.albertsonsapp.model.repository.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AcronymViewModel @Inject constructor(private val repository: AcronymRepository): ViewModel() {

    private val _uiState = MutableStateFlow<UIState>(UIState.Empty)
    val uiState: StateFlow<UIState>
    get() = _uiState

    fun fetchAcronymList(acronymWord: String) {
        viewModelScope.launch(exceptionHandler){
            repository.getAcronm(acronymWord).collect{
                _uiState.value = it
            }
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler{_, throwable ->
        onError("Exception: ${throwable.localizedMessage}")
    }

    private fun onError(error: String){
        _uiState.value = UIState.Failure(error)
    }
}