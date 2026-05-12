package com.madhumarga.app.ui.harvest

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.madhumarga.app.data.HarvestViewModel
import com.madhumarga.app.data.HiveViewModel
import com.madhumarga.app.data.model.Harvest
import com.madhumarga.app.databinding.ActivityAddHarvestBinding
import kotlinx.coroutines.launch

class AddHarvestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddHarvestBinding
    private lateinit var harvestViewModel: HarvestViewModel
    private lateinit var hiveViewModel: HiveViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddHarvestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Log Harvest"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        harvestViewModel = ViewModelProvider(this)[HarvestViewModel::class.java]
        hiveViewModel = ViewModelProvider(this)[HiveViewModel::class.java]

        lifecycleScope.launch {
            val hives = hiveViewModel.getAllHivesList()
            val names = hives.map { it.name }.toTypedArray()
            binding.spinnerHive.setAdapter(
                ArrayAdapter(this@AddHarvestActivity, android.R.layout.simple_dropdown_item_1line, names)
            )
            if (names.isNotEmpty()) binding.spinnerHive.setText(names[0], false)
        }

        binding.ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            binding.tvRatingLabel.text = "Quality: ${rating.toInt()}/5 ⭐"
        }
        binding.ratingBar.rating = 4f

        binding.btnSave.setOnClickListener { saveHarvest() }
    }

    private fun saveHarvest() {
        val hiveName = binding.spinnerHive.text.toString().trim()
        val quantityStr = binding.etQuantity.text.toString().trim()

        if (hiveName.isEmpty()) {
            Toast.makeText(this, "Please select a hive", Toast.LENGTH_SHORT).show()
            return
        }
        if (quantityStr.isEmpty()) {
            binding.etQuantity.error = "Quantity required"
            return
        }

        val harvest = Harvest(
            hiveId = 0,
            hiveName = hiveName,
            quantityKg = quantityStr.toFloat(),
            qualityRating = binding.ratingBar.rating.toInt(),
            notes = binding.etNotes.text.toString().trim()
        )

        harvestViewModel.insert(harvest)
        Toast.makeText(this, "Harvest logged! 🍯", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
