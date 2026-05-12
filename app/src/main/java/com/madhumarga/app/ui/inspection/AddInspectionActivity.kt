package com.madhumarga.app.ui.inspection

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.madhumarga.app.data.HiveViewModel
import com.madhumarga.app.data.InspectionViewModel
import com.madhumarga.app.data.model.Inspection
import com.madhumarga.app.databinding.ActivityAddInspectionBinding
import kotlinx.coroutines.launch

class AddInspectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddInspectionBinding
    private lateinit var inspectionViewModel: InspectionViewModel
    private lateinit var hiveViewModel: HiveViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddInspectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Log Inspection"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        inspectionViewModel = ViewModelProvider(this)[InspectionViewModel::class.java]
        hiveViewModel = ViewModelProvider(this)[HiveViewModel::class.java]

        val levels = arrayOf("Low", "Normal", "High")
        binding.spinnerHoneyFlow.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, levels))
        binding.spinnerActivity.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, levels))
        binding.spinnerHoneyFlow.setText("Normal", false)
        binding.spinnerActivity.setText("Normal", false)

        // Load hives for dropdown
        lifecycleScope.launch {
            val hives = hiveViewModel.getAllHivesList()
            val hiveNames = hives.map { it.name }.toTypedArray()
            val hiveAdapter = ArrayAdapter(this@AddInspectionActivity, android.R.layout.simple_dropdown_item_1line, hiveNames)
            binding.spinnerHive.setAdapter(hiveAdapter)
            if (hiveNames.isNotEmpty()) binding.spinnerHive.setText(hiveNames[0], false)
        }

        binding.switchPests.setOnCheckedChangeListener { _, checked ->
            binding.tilPestType.visibility = if (checked) android.view.View.VISIBLE else android.view.View.GONE
        }

        binding.btnSave.setOnClickListener { saveInspection() }
    }

    private fun saveInspection() {
        val hiveName = binding.spinnerHive.text.toString().trim()
        if (hiveName.isEmpty()) {
            Toast.makeText(this, "Please select a hive", Toast.LENGTH_SHORT).show()
            return
        }

        val inspection = Inspection(
            hiveId = 0, // simplified - use hive name as key
            hiveName = hiveName,
            queenSeen = binding.switchQueenSeen.isChecked,
            eggsPresent = binding.switchEggs.isChecked,
            pestsSeen = binding.switchPests.isChecked,
            pestType = binding.etPestType.text.toString().trim(),
            honeyFlow = binding.spinnerHoneyFlow.text.toString().ifEmpty { "Normal" },
            activityLevel = binding.spinnerActivity.text.toString().ifEmpty { "Normal" },
            temperature = binding.etTemperature.text.toString().toFloatOrNull() ?: 0f,
            humidity = binding.etHumidity.text.toString().toFloatOrNull() ?: 0f,
            notes = binding.etNotes.text.toString().trim()
        )

        inspectionViewModel.insert(inspection)
        Toast.makeText(this, "Inspection logged!", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
