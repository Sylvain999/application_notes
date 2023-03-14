package com.example.notesmotivation.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity(tableName = "plannedActivities")
data class PlannedActivity(

    @ColumnInfo(name = "title")
    var title: String,

    @TypeConverters(LocalDateConverter::class)
    @ColumnInfo(name = "date")
    var date: LocalDate
) {

    @PrimaryKey(autoGenerate = true)
    var activityId: Int = 0

    override fun toString(): String {
        return "title : $title; date: " + date.format(DateTimeFormatter.ofPattern("dd MM yyyy"))
    }

    fun isCorrect() : Boolean {
        return title.isNotEmpty() &&
                (date.isEqual(LocalDate.now()) || date.isAfter(LocalDate.now()))
    }
}