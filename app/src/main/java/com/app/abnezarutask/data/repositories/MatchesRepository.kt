package com.app.abnezarutask.data.repositories

import com.app.abnezarutask.db.entity.AcceptOrDeclineStatus
import com.app.abnezarutask.models.MatchesApiResponseModel
import com.app.abnezarutask.network.AppResult

interface MatchesRepository {
    suspend fun getResults(result: Int): AppResult<MatchesApiResponseModel>

    suspend fun saveAcceptOrDeclineStatus(data: AcceptOrDeclineStatus)

    suspend fun saveResults(data: List<com.app.abnezarutask.models.Result>)

    suspend fun getResultsFromLocal(): List<com.app.abnezarutask.models.Result>

    suspend fun getAcceptedOrDeclinedStatusList(): List<AcceptOrDeclineStatus>
}