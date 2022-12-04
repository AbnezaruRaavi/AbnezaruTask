package com.app.abnezarutask.data.repositoriesImpl

import com.app.abnezarutask.data.repositories.MatchesRepository
import com.app.abnezarutask.db.AppDataBase
import com.app.abnezarutask.db.entity.AcceptOrDeclineStatus
import com.app.abnezarutask.models.MatchesApiResponseModel
import com.app.abnezarutask.models.Result
import com.app.abnezarutask.network.ApiServices
import com.app.abnezarutask.network.AppResult
import com.app.abnezarutask.network.handleApiError
import com.app.abnezarutask.network.handleSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MatchesRepositoryImpl(
    private val apiServices: ApiServices,
    private val appDataBase: AppDataBase
) : MatchesRepository {

    override suspend fun getResults(result: Int): AppResult<MatchesApiResponseModel> {
        val response = apiServices.getResults(result)
        return if (response.isSuccessful) {
            handleSuccess(response)
        } else
            handleApiError(response)
    }

    override suspend fun saveAcceptOrDeclineStatus(data: AcceptOrDeclineStatus) {
        withContext(Dispatchers.IO) {
            launch {
                appDataBase.getMatchesDao().insertAcceptedOrDeclinedStatus(data)
            }
        }
    }

    override suspend fun saveResults(data: List<Result>) {
        withContext(Dispatchers.IO) {
            launch {
                appDataBase.getMatchesDao().insertData(data)
            }
        }
    }

    override suspend fun getResultsFromLocal(): List<Result> {
        return appDataBase.getMatchesDao().getMatchesResults()
    }

    override suspend fun getAcceptedOrDeclinedStatusList(): List<AcceptOrDeclineStatus> {
        return appDataBase.getMatchesDao().getAcceptedOrDeclinedStatusList()
    }

}