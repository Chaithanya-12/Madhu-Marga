package com.madhumarga.app.ui.hive

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.madhumarga.app.data.HiveViewModel
import com.madhumarga.app.data.model.Hive
import com.madhumarga.app.databinding.FragmentHivesBinding

class HivesFragment : Fragment() {

    private var _binding: FragmentHivesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HiveViewModel
    private lateinit var adapter: HiveAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHivesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[HiveViewModel::class.java]

        adapter = HiveAdapter(
            onEdit = { hive ->
                val intent = Intent(requireContext(), AddEditHiveActivity::class.java)
                intent.putExtra("hive_id", hive.id)
                startActivity(intent)
            },
            onDelete = { hive ->
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Delete Hive")
                    .setMessage("Delete '${hive.name}'? This cannot be undone.")
                    .setPositiveButton("Delete") { _, _ -> viewModel.delete(hive) }
                    .setNegativeButton("Cancel", null)
                    .show()
            }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        viewModel.allHives.observe(viewLifecycleOwner) { hives ->
            adapter.submitList(hives)
            binding.emptyState.visibility = if (hives.isEmpty()) View.VISIBLE else View.GONE
        }

        binding.fab.setOnClickListener {
            startActivity(Intent(requireContext(), AddEditHiveActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
