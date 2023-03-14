package com.example.notesmotivation.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PlannedActivity::class], version = 1)
@TypeConverters(LocalDateConverter::class)
abstract class PlannedActivityDatabase : RoomDatabase() {

    abstract fun activityDao() : ActivityDao

    companion object {
        @Volatile
        private var INSTANCE: PlannedActivityDatabase? = null

        fun getDatabase(context : Context) : PlannedActivityDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlannedActivityDatabase::class.java,
                    "my_database"
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }

        }
    }
}