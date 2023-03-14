package com.example.notesmotivation.data

import androidx.room.TypeConverter
import java.sql.Date
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class LocalDateConverter {

    @TypeConverter
    fun localDateToLong(localDate: LocalDate) : Long {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()).time
    }

    @TypeConverter
    fun dateToLocalLong(timestamp: Long) : LocalDate {
        return Instant.ofEpochMilli(timestamp)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    }

}