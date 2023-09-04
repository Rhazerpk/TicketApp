package com.kotlin.ticketapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Clients")
data class Client(
    @PrimaryKey
    val clientId:Int? = null,
    var name: String = ""
)