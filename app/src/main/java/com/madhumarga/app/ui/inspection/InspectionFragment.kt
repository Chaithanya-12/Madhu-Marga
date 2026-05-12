package com.madhumarga.app.ui.inspection
import android.widget.Toast
import com.madhumarga.app.ai.GeminiHelper
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.madhumarga.app.data.InspectionViewModel
import com.madhumarga.app.databinding.FragmentInspectionBinding

class InspectionFragment : Fragment() {

    private var _binding: FragmentInspectionBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: InspectionViewModel
    private lateinit var adapter: InspectionAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentInspectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[InspectionViewModel::class.java]

        adapter = InspectionAdapter { inspection ->
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Delete Inspection")
                .setMessage("Delete this inspection log?")
                .setPositiveButton("Delete") { _, _ -> viewModel.delete(inspection) }
                .setNegativeButton("Cancel", null)
                .show()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        viewModel.allInspections.observe(viewLifecycleOwner) { inspections ->
            adapter.submitList(inspections)
            binding.emptyState.visibility = if (inspections.isEmpty()) View.VISIBLE else View.GONE
        }

        binding.fab.setOnClickListener {
            startActivity(Intent(requireContext(), AddInspectionActivity::class.java))
        }
        binding.fab.setOnLongClickListener {

            GeminiHelper.ask("How to improve honey production?") {
                requireActivity().runOnUiThread {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                }
            }

            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
