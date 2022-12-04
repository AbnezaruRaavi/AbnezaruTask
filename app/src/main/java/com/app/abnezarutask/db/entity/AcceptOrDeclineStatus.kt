package com.app.abnezarutask.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AcceptOrDeclineStatus(
    val uuid: String,
    val isAccepted: Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Int= 0
}
