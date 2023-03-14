package com.example.notesmotivation.activities.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleAdapter
import com.example.notesmotivation.R
import com.example.notesmotivation.data.PlannedActivity
import com.example.notesmotivation.data.PlannedActivityDatabase
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ListActivitiesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list_activities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val params = getListPlannedActivities()



        val simpleAdapter = SimpleAdapter(activity,
            params,
            R.layout.item_activity,
            arrayOf("title", "date"),
            intArrayOf(R.id.titleActivity, R.id.contentActivity)
        )

        view.findViewById<ListView>(R.id.list_items).adapter = simpleAdapter
    }

    fun getListPlannedActivities() : ArrayList<HashMap<String,String>> {
        val plannedActivityDatabase = PlannedActivityDatabase.getDatabase(requireContext())

        val listPlannedActivity = plannedActivityDatabase.activityDao().getActivitiesOfDate(LocalDate.now())

        Log.d("TEMP", listPlannedActivity.toString())

        val params = ArrayList<HashMap<String,String>>()

        for (item in listPlannedActivity) {
            val map = HashMap<String,String>();
            map["title"] = item.title
            map["date"] = item.date.format(DateTimeFormatter.ofPattern("dd MM YYYY"))

            params.add(map)
        }

        return params
    }
}