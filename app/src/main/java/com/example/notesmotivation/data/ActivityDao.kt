package com.example.notesmotivation.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import java.time.LocalDate

@Dao
interface ActivityDao {
    @Insert
    fun insert(plannedActivity: PlannedActivity)

    @Query("SELECT * FROM plannedActivities ORDER BY date DESC")
    fun getActivitiesByDate() : List<PlannedActivity>

    @Delete
    fun delete(plannedActivity: PlannedActivity)

    @Query("DELETE FROM plannedActivities")
    fun deleteAll()

    @Query("SELECT * FROM plannedActivities WHERE date = :dateParam")
    fun getActivitiesOfDate(dateParam : LocalDate) : List<PlannedActivity>

}