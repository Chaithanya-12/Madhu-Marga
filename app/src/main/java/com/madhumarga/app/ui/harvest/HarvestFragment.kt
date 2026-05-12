package com.madhumarga.app.ui.harvest

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.madhumarga.app.data.HarvestViewModel
import com.madhumarga.app.databinding.FragmentHarvestBinding

class HarvestFragment : Fragment() {

    private var _binding: FragmentHarvestBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HarvestViewModel
    private lateinit var adapter: HarvestAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHarvestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[HarvestViewModel::class.java]

        adapter = HarvestAdapter { harvest ->
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Delete Harvest Record")
                .setMessage("Delete this harvest log?")
                .setPositiveButton("Delete") { _, _ -> viewModel.delete(harvest) }
                .setNegativeButton("Cancel", null)
                .show()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        viewModel.allHarvests.observe(viewLifecycleOwner) { harvests ->
            adapter.submitList(harvests)
            binding.emptyState.visibility = if (harvests.isEmpty()) View.VISIBLE else View.GONE
        }

        viewModel.totalHarvest.observe(viewLifecycleOwner) { total ->
            binding.tvTotalHarvest.text = "Total: ${String.format("%.2f", total ?: 0f)} kg"
        }

        // Yearly comparison bar chart
        viewModel.yearlyHarvests.observe(viewLifecycleOwner) { yearlyData ->
            if (yearlyData != null && yearlyData.isNotEmpty()) {
                binding.barChart.visibility = View.VISIBLE
                val entries = yearlyData.mapIndexed { i, y -> BarEntry(i.toFloat(), y.total) }
                val labels = yearlyData.map { it.year }
                val dataSet = BarDataSet(entries, "Honey (kg)").apply {
                    color = resources.getColor(com.madhumarga.app.R.color.honey_amber, null)
                    valueTextSize = 12f
                }
                binding.barChart.apply {
                    data = BarData(dataSet)
                    xAxis.valueFormatter = IndexAxisValueFormatter(labels)
                    xAxis.granularity = 1f
                    xAxis.position = com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM
                    axisRight.isEnabled = false
                    description.isEnabled = false
                    animateY(800)
                    invalidate()
                }
            } else {
                binding.barChart.visibility = View.GONE
            }
        }

        // Honey flow progress bar
        viewModel.allHarvests.observe(viewLifecycleOwner) { harvests ->
            if (harvests.isNotEmpty()) {
                val maxGoal = 100f
                val total = harvests.sumOf { it.quantityKg.toDouble() }.toFloat()
                val progress = ((total / maxGoal) * 100).toInt().coerceAtMost(100)
                binding.progressHoneyFlow.progress = progress
                binding.tvFlowProgress.text = "Season Progress: ${String.format("%.1f", total)} kg / ${maxGoal.toInt()} kg goal"
            }
        }

        binding.fab.setOnClickListener {
            startActivity(Intent(requireContext(), AddHarvestActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
