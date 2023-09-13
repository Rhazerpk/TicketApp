package com.kotlin.ticketapp.data.local.entities
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "Clients")
data class Client(
    @PrimaryKey(autoGenerate = true)
    val clientId:Int = 0,
    var name: String = "",
    var phoneNumber: String = "",
    var cellNumber: String = "",
    val email: String = "",
    val address: String = "",
    val birthdate: Date,
    val occupation:String = ""
)
