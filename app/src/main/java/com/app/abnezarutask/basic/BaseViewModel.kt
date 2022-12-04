package com.app.abnezarutask.basic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.abnezarutask.data.repositories.MatchesRepository
import com.app.abnezarutask.models.MatchesApiResponseModel
import com.app.abnezarutask.network.AppResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    private val _progressIndicator = MutableStateFlow(false)
    var progressIndicator: StateFlow<Boolean> = _progressIndicator.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    var errorMessage: StateFlow<String> = _errorMessage.asStateFlow()


    fun callMatchesListApi(matchesRepo : MatchesRepository, index : Int, callback : (MatchesApiResponseModel)->Unit){
        _progressIndicator.value = true
        viewModelScope.launch {
            when (val result = matchesRepo.getResults(index)) {
                is AppResult.Success -> {
                    callback(result.successData)
                    _progressIndicator.value = false
                }
                is AppResult.Error -> {
                    _errorMessage.value = result.message
                    _progressIndicator.value = false
                }
            }
        }
    }

}