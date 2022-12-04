package com.app.abnezarutask.utils

import androidx.room.TypeConverter
import com.app.abnezarutask.models.*
import com.google.gson.Gson

class TypeConverters{
    @TypeConverter
    fun nameToString(name: Name): String {
        return Gson().toJson(name)
    }

    @TypeConverter
    fun stringToName(value: String): Name {
        return Gson().fromJson(value,Name::class.java)
    }

    @TypeConverter
    fun objectToString(location: Location): String {
        return Gson().toJson(location)
    }

    @TypeConverter
    fun stringToLocation(value: String): Location {
        return Gson().fromJson(value,Location::class.java)
    }

    @TypeConverter
    fun objectToString(login: Login): String {
        return Gson().toJson(login)
    }

    @TypeConverter
    fun stringToLogin(value: String): Login {
        return Gson().fromJson(value,Login::class.java)
    }

    @TypeConverter
    fun objectToString(dob: Dob): String {
        return Gson().toJson(dob)
    }

    @TypeConverter
    fun stringToDob(value: String): Dob {
        return Gson().fromJson(value,Dob::class.java)
    }

    @TypeConverter
    fun objectToString(id: ID): String {
        return Gson().toJson(id)
    }

    @TypeConverter
    fun stringToId(value: String): ID {
        return Gson().fromJson(value,ID::class.java)
    }

    @TypeConverter
    fun objectToString(picture: Picture): String {
        return Gson().toJson(picture)
    }

    @TypeConverter
    fun stringToPicture(value: String): Picture {
        return Gson().fromJson(value,Picture::class.java)
    }
}