package com.example.notesmotivation.activities

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.notesmotivation.R
import com.example.notesmotivation.databinding.ActivityAddBinding
import com.example.notesmotivation.viewModels.AddViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*

class AddActivity : AppCompatActivity() {

    /**
     * The date that user can edit
     */
    private lateinit var dateText: TextView

    /**
     * The title view
     */
    private lateinit var title: EditText

    /**
     * The view model
     */
    private lateinit var plannedActivityViewModel : AddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        plannedActivityViewModel = ViewModelProvider(this)[AddViewModel::class.java]

        plannedActivityViewModel.loadDatabase(this)

        val binding = ActivityAddBinding.inflate(layoutInflater)
        binding.viewModel = plannedActivityViewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        initCreateButton()
        initDatePicker()
    }

    private fun initCreateButton() {

        findViewById<Button>(R.id.create_activity).setOnClickListener {

            if (plannedActivityViewModel.addToDB()) {
                finish()
            } else {
                Toast.makeText(this@AddActivity, "Tous les champs n'ont pas été remplis", Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun initDatePicker() {
        dateText = findViewById(R.id.date_to_add)

        // we create the function that will be called when date is chosen
        val setListener =
            DatePickerDialog.OnDateSetListener { _, yearParam, monthParam, dayOfMonth ->

                plannedActivityViewModel.setDate(LocalDate.of(yearParam, monthParam + 1, dayOfMonth))

                dateText.text = plannedActivityViewModel.getDate()

            }

        // Define whats the date today
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // set the function when we click on the button
        dateText.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                com.google.android.material.R.style.MaterialAlertDialog_MaterialComponents_Picker_Date_Calendar,
                setListener,
                year,
                month,
                day
            )

            datePickerDialog.show()

        }


    }
}