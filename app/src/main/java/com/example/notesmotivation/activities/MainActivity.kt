package com.example.notesmotivation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.notesmotivation.R
import com.example.notesmotivation.activities.fragments.ListActivitiesFragment
import com.example.notesmotivation.activities.fragments.NoActivitiesFragment
import com.example.notesmotivation.data.PlannedActivityDatabase
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    val TAG = "MAIN_ACTIVITY"
    lateinit var plannedActivityDatabase: PlannedActivityDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plannedActivityDatabase = PlannedActivityDatabase.getDatabase(this)

        initButtonAddActivity()

        placeFragment()

    }

    override fun onResume() {
        super.onResume()

        placeFragment()
    }

    fun initButtonAddActivity() {
        // button add activity
        findViewById<ImageButton>(R.id.button_add)
            .setOnClickListener {

                Log.d(TAG, "Go to Add Activity")

                val intent = Intent(this, AddActivity::class.java)

                startActivity(intent)

            }
    }

    fun placeFragment() {

        val listPlannedActivity = plannedActivityDatabase.activityDao().getActivitiesOfDate(LocalDate.now())

        var fragment = if (listPlannedActivity.isEmpty()) {
            NoActivitiesFragment()
        } else {
            ListActivitiesFragment()
        }

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_view_tag, fragment )
            commit()
        }


    }

}