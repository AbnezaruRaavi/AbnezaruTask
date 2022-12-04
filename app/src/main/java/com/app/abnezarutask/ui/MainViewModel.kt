package com.app.abnezarutask.ui

import androidx.lifecycle.viewModelScope
import com.app.abnezarutask.basic.BaseViewModel
import com.app.abnezarutask.data.repositories.MatchesRepository
import com.app.abnezarutask.db.entity.AcceptOrDeclineStatus
import com.app.abnezarutask.models.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val matchesRepository: MatchesRepository) : BaseViewModel() {

    private var paginationIndex = 10

    private  var _apiResult= MutableStateFlow(ArrayList<Result>())
    var apiResult : StateFlow<ArrayList<Result>> = _apiResult.asStateFlow()


    fun callMatchesApi(){
        getAcceptedOrDeclineStatusFromLocal{dataMap->
            callMatchesListApi(matchesRepository, paginationIndex){
                it.results.map { result ->
                    result.isAccepted = dataMap[result.login.uuid]?:0
                }
                _apiResult.value = it.results
                savaResultsLocal()
            }
        }
    }

    fun saveAcceptOrDeclineStatusLocal(position: Int, isAccepted:Boolean){
        viewModelScope.launch {
            val data = AcceptOrDeclineStatus(uuid = _apiResult.value[position].login.uuid, isAccepted = if (isAccepted) 1 else 2)
            matchesRepository.saveAcceptOrDeclineStatus(data)
            apiResult.value[position].isAccepted = if (isAccepted) 1 else 2
        }
    }

    private fun savaResultsLocal(){
        viewModelScope.launch {
            matchesRepository.saveResults(_apiResult.value)
        }
    }

    fun getResultsFromLocal(){
        getAcceptedOrDeclineStatusFromLocal{dataMap->
            viewModelScope.launch(Dispatchers.IO) {
                val data = matchesRepository.getResultsFromLocal() as ArrayList<Result>
                data.map { result ->
                    result.isAccepted = dataMap[result.login.uuid]?:0
                }
                _apiResult.value = data
            }
        }
    }

    private fun getAcceptedOrDeclineStatusFromLocal(callback : (Map<String, Int>)->Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val data =  matchesRepository.getAcceptedOrDeclinedStatusList() as ArrayList<AcceptOrDeclineStatus>
            callback(data.associate { it.uuid to it.isAccepted })
        }
    }

}