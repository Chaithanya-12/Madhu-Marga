package com.madhumarga.app.ui.hive

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.madhumarga.app.data.HiveViewModel
import com.madhumarga.app.data.model.Hive
import com.madhumarga.app.databinding.ActivityAddEditHiveBinding
import kotlinx.coroutines.launch

class AddEditHiveActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditHiveBinding
    private lateinit var viewModel: HiveViewModel
    private var hiveId: Int = -1
    private var existingHive: Hive? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditHiveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this)[HiveViewModel::class.java]
        hiveId = intent.getIntExtra("hive_id", -1)

        val activityLevels = arrayOf("Low", "Normal", "High")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, activityLevels)
        binding.spinnerActivity.setAdapter(adapter)

        if (hiveId != -1) {
            supportActionBar?.title = "Edit Hive"
            lifecycleScope.launch {
                existingHive = viewModel.getHiveById(hiveId)
                existingHive?.let { populateFields(it) }
            }
        } else {
            supportActionBar?.title = "Add New Hive"
            binding.spinnerActivity.setText("Normal", false)
        }

        binding.btnSave.setOnClickListener { saveHive() }
    }

    private fun populateFields(hive: Hive) {
        binding.etName.setText(hive.name)
        binding.etLocation.setText(hive.location)
        binding.switchQueen.isChecked = hive.queenPresent
        binding.spinnerActivity.setText(hive.activityLevel, false)
        binding.etNotes.setText(hive.notes)
    }

    private fun saveHive() {
        val name = binding.etName.text.toString().trim()
        val location = binding.etLocation.text.toString().trim()
        val activityLevel = binding.spinnerActivity.text.toString().trim().ifEmpty { "Normal" }

        if (name.isEmpty()) {
            binding.etName.error = "Hive name is required"
            return
        }
        if (location.isEmpty()) {
            binding.etLocation.error = "Location is required"
            return
        }

        val hive = Hive(
            id = if (hiveId != -1) hiveId else 0,
            name = name,
            location = location,
            queenPresent = binding.switchQueen.isChecked,
            activityLevel = activityLevel,
            notes = binding.etNotes.text.toString().trim(),
            createdAt = existingHive?.createdAt ?: System.currentTimeMillis()
        )

        if (hiveId != -1) viewModel.update(hive) else viewModel.insert(hive)
        Toast.makeText(this, if (hiveId != -1) "Hive updated!" else "Hive added!", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
