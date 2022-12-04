package com.app.abnezarutask.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.abnezarutask.db.entity.AcceptOrDeclineStatus

@Dao
interface MatchesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(data: List<com.app.abnezarutask.models.Result>)

    @Insert
    fun insertAcceptedOrDeclinedStatus(data: AcceptOrDeclineStatus)

    @Query("SELECT * FROM Result")
    fun getMatchesResults(): List<com.app.abnezarutask.models.Result>

    @Query("SELECT * FROM AcceptOrDeclineStatus")
    fun getAcceptedOrDeclinedStatusList(): List<AcceptOrDeclineStatus>
}