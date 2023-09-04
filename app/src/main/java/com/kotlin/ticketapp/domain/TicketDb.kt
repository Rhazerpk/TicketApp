package com.kotlin.ticketapp.domain
import androidx.room.RoomDatabase
import com.kotlin.ticketapp.data.local.dao.TicketDao

abstract  class TicketDb : RoomDatabase(){
    abstract fun ticketDao(): TicketDao
}