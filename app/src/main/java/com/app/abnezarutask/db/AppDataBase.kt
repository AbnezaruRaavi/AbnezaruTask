package com.app.abnezarutask.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.abnezarutask.db.entity.AcceptOrDeclineStatus

@Database(
    entities = [com.app.abnezarutask.models.Result::class, AcceptOrDeclineStatus::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(com.app.abnezarutask.utils.TypeConverters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getMatchesDao(): MatchesDao
}