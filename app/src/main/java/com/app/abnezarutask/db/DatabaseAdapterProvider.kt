package com.app.abnezarutask.db

import android.content.Context
import androidx.room.Room
import com.app.abnezarutask.R

object DatabaseAdapterProvider {
    fun provideDatabase(context: Context): AppDataBase{
           return Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                context.getString(R.string.DB_NAME)
            ).build()}

}