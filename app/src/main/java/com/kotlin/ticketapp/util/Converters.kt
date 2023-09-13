package com.kotlin.ticketapp.util
import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date
import java.text.ParseException

class Converters {
    @TypeConverter
    fun dateToString(date: Date?): String? {
        return date?.let { SimpleDateFormat("yyyy-MM-dd").format(it) }
    }

    @TypeConverter
    fun stringToDate(dateStr: String?): Date? {
        return try {
            dateStr?.let { SimpleDateFormat("yyyy-MM-dd").parse(it) }
        } catch (e: ParseException) {
            null
        }
    }
}
