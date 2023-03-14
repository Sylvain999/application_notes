package com.example.notesmotivation.viewModels
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.example.notesmotivation.data.PlannedActivity
import com.example.notesmotivation.data.PlannedActivityDatabase
import com.example.notesmotivation.utils.ObservableViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AddViewModel(app: Application) : ObservableViewModel(app) {

    private val TAG = "ADD_ACTIVITY"

    private val _plannedActivity = MutableLiveData<PlannedActivity>()

    private lateinit var plannedActivityDatabase: PlannedActivityDatabase

    val plannedActivity : MutableLiveData<PlannedActivity>
        get() = _plannedActivity

    init {
        _plannedActivity.value = PlannedActivity("", LocalDate.now())
    }

    fun getDate() : String {
        return _plannedActivity.value?.date!!.format(DateTimeFormatter.ofPattern("dd MM yyyy"))
    }

    fun loadDatabase(context : Context) {
        plannedActivityDatabase = PlannedActivityDatabase.getDatabase(context)
    }

    fun addToDB() : Boolean {
        if (!this._plannedActivity.value?.isCorrect()!!) {
            return false
        }

        Log.d(TAG, _plannedActivity.value.toString() + "is added")

        plannedActivityDatabase.activityDao().insert(_plannedActivity.value!!)

        return true
    }

    fun setDate(localDate: LocalDate) {
        _plannedActivity.value?.date = localDate
    }

    @Bindable
    fun getTitle() : String {
        return _plannedActivity.value?.title!!
    }

    fun setTitle(title: String) {
        plannedActivity.value?.title = title
    }
}