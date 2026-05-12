package com.madhumarga.app.ui.flora

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.madhumarga.app.data.FloraViewModel
import com.madhumarga.app.data.model.Flora
import com.madhumarga.app.databinding.ActivityAddFloraBinding
import com.madhumarga.app.databinding.FragmentFloraBinding

class FloraFragment : Fragment() {

    private var _binding: FragmentFloraBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FloraViewModel
    private lateinit var adapter: FloraAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFloraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[FloraViewModel::class.java]

        adapter = FloraAdapter { flora ->
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Delete Flora")
                .setMessage("Remove '${flora.flowerName}' from the calendar?")
                .setPositiveButton("Delete") { _, _ -> viewModel.delete(flora) }
                .setNegativeButton("Cancel", null)
                .show()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        viewModel.allFlora.observe(viewLifecycleOwner) { flora ->
            adapter.submitList(flora)
            binding.emptyState.visibility = if (flora.isEmpty()) View.VISIBLE else View.GONE
        }

        binding.fab.setOnClickListener { showAddFloraSheet() }
    }

    private fun showAddFloraSheet() {
        val dialog = BottomSheetDialog(requireContext())
        val sheetBinding = ActivityAddFloraBinding.inflate(layoutInflater)
        dialog.setContentView(sheetBinding.root)

        val seasons = arrayOf("Jan-Feb", "Feb-Mar", "Mar-Apr", "Apr-May", "May-Jun",
            "Jun-Jul", "Jul-Aug", "Aug-Sep", "Sep-Oct", "Oct-Nov", "Nov-Dec", "Dec-Jan", "Year-round")
        sheetBinding.spinnerSeason.setAdapter(
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, seasons)
        )
        sheetBinding.spinnerSeason.setText("Mar-Apr", false)
        sheetBinding.ratingBar.rating = 3f

        sheetBinding.btnSave.setOnClickListener {
            val name = sheetBinding.etFlowerName.text.toString().trim()
            if (name.isEmpty()) {
                sheetBinding.etFlowerName.error = "Flower name required"
                return@setOnClickListener
            }
            val flora = Flora(
                flowerName = name,
                bloomingSeason = sheetBinding.spinnerSeason.text.toString(),
                nectarRating = sheetBinding.ratingBar.rating.toInt(),
                distanceKm = sheetBinding.etDistance.text.toString().toFloatOrNull() ?: 0f,
                location = sheetBinding.etLocation.text.toString().trim(),
                notes = sheetBinding.etNotes.text.toString().trim()
            )
            viewModel.insert(flora)
            Toast.makeText(requireContext(), "Flora added 🌸", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
