package com.app.abnezarutask.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.abnezarutask.basic.ListItemModel

data class MatchesApiResponseModel (
    val results: ArrayList<Result>,
    val info: Info
)


data class Info (
    val seed: String,
    val results: String,
    val page: String,
    val version: String
)

@Entity
data class Result (
    @PrimaryKey(autoGenerate = true)
    var pid: Int,
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val login: Login,
    val dob: Dob,
    val registered: Dob,
    val phone: String,
    val cell: String,
    val id: ID,
    val picture: Picture,
    val nat: String,
    var isAccepted : Int = 0
) : ListItemModel()


data class Dob (
    val date: String,
    val age: String
)


data class ID (
    val name: String,
    val value: String? = null
)


data class Location (
    val street: Street,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: Coordinates,
    val timezone: Timezone
)


data class Coordinates (
    val latitude: String,
    val longitude: String
)

data class Street (
    val number: String,
    val name: String
)


data class Timezone (
    val offset: String,
    val description: String
)


data class Login (
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String
)


data class Name (
    val title: String,
    val first: String,
    val last: String
)


data class Picture (
    val large: String,
    val medium: String,
    val thumbnail: String
)

