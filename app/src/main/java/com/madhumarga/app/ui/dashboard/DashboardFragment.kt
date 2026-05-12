package com.madhumarga.app.ui.dashboard

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.madhumarga.app.R
import com.madhumarga.app.data.HarvestViewModel
import com.madhumarga.app.data.HiveViewModel
import com.madhumarga.app.data.InspectionViewModel
import com.madhumarga.app.databinding.FragmentDashboardBinding
import com.madhumarga.app.ui.ai.AiActivity
import java.util.Calendar

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var hiveViewModel: HiveViewModel
    private lateinit var inspectionViewModel: InspectionViewModel
    private lateinit var harvestViewModel: HarvestViewModel
    private lateinit var alertAdapter: AlertAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hiveViewModel = ViewModelProvider(requireActivity())[HiveViewModel::class.java]
        inspectionViewModel = ViewModelProvider(requireActivity())[InspectionViewModel::class.java]
        harvestViewModel = ViewModelProvider(requireActivity())[HarvestViewModel::class.java]

        alertAdapter = AlertAdapter()
        binding.rvAlerts.layoutManager = LinearLayoutManager(context)
        binding.rvAlerts.adapter = alertAdapter

        setupObservers()
        binding.aiButton.setOnClickListener {
            startActivity(Intent(requireContext(), AiActivity::class.java))
        }
    }

    private fun setupObservers() {
        hiveViewModel.hiveCount.observe(viewLifecycleOwner) { count ->
            binding.tvHiveCount.text = count.toString()
        }

        inspectionViewModel.inspectionCount.observe(viewLifecycleOwner) { count ->
            binding.tvInspectionCount.text = count.toString()
        }

        harvestViewModel.totalHarvest.observe(viewLifecycleOwner) { total ->
            binding.tvTotalHarvest.text = String.format("%.1f kg", total ?: 0f)
        }

        val currentYear = Calendar.getInstance().get(Calendar.YEAR).toString()
        val lastYear = (currentYear.toInt() - 1).toString()
        harvestViewModel.getHarvestByYear(currentYear).observe(viewLifecycleOwner) { current ->
            harvestViewModel.getHarvestByYear(lastYear).observe(viewLifecycleOwner) { last ->
                val c = current ?: 0f
                val l = last ?: 0f
                if (l > 0) {
                    val pct = ((c - l) / l * 100).toInt()
                    val sign = if (pct >= 0) "+" else ""
                    binding.tvYoyComparison.text = "YoY: $sign$pct% vs $lastYear"
                    binding.tvYoyComparison.setTextColor(
                        resources.getColor(
                            if (pct >= 0) android.R.color.holo_green_dark
                            else android.R.color.holo_red_dark, null
                        )
                    )
                } else {
                    binding.tvYoyComparison.text = "$currentYear: ${String.format("%.1f", c)} kg"
                }
            }
        }

        inspectionViewModel.lowActivityInspections.observe(viewLifecycleOwner) { inspections ->
            if (inspections.isNotEmpty()) {
                binding.cardAlerts.visibility = View.VISIBLE
                val alerts = inspections.map { insp ->
                    AlertItem(
                        insp.hiveName,
                        "⚠️ Low Activity detected on ${formatDate(insp.inspectionDate)}. Consider: Check for queen presence, inspect for disease, ensure adequate food.",
                        AlertType.WARNING
                    )
                }
                alertAdapter.submitList(alerts)
                binding.tvAlertCount.text = "${alerts.size} alert(s)"
                sendHiveNotification(
                    "⚠️ Hive Alert",
                    "${inspections.size} hive(s) showing low activity. Check your dashboard."
                )
            } else {
                binding.cardAlerts.visibility = View.GONE
            }
        }

        inspectionViewModel.recentInspections.observe(viewLifecycleOwner) { inspections ->
            val pestCount = inspections.count { it.pestsSeen }
            if (pestCount > 0) {
                binding.cardPestAlert.visibility = View.VISIBLE
                binding.tvPestAlert.text = "🐛 Pest sightings in $pestCount recent inspection(s)!"
            } else {
                binding.cardPestAlert.visibility = View.GONE
            }
        }
    }

    private fun sendHiveNotification(title: String, message: String) {
        val prefs = requireContext().getSharedPreferences("madhumarga_prefs", Context.MODE_PRIVATE)
        if (!prefs.getBoolean("notifications_enabled", true)) return

        val manager = requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(requireContext(), "madhumarga_alerts")
            .setSmallIcon(R.drawable.ic_bee)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()
        manager.notify(System.currentTimeMillis().toInt(), notification)
    }

    private fun formatDate(timestamp: Long): String {
        val cal = Calendar.getInstance()
        cal.timeInMillis = timestamp
        return "${cal.get(Calendar.DAY_OF_MONTH)}/${cal.get(Calendar.MONTH) + 1}/${cal.get(Calendar.YEAR)}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}