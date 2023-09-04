package com.kotlin.ticketapp.domain
import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlin.ticketapp.data.local.dao.TicketDao
import com.kotlin.ticketapp.data.local.entities.Client

@Database(
    entities = [Client::class],
    version = 1
)
abstract  class TicketDb : RoomDatabase(){
    abstract fun ticketDao(): TicketDao
}