package com.madhumarga.app.ui.harvest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madhumarga.app.data.model.Harvest
import com.madhumarga.app.databinding.ItemHarvestBinding
import java.text.SimpleDateFormat
import java.util.*

class HarvestAdapter(
    private val onDelete: (Harvest) -> Unit
) : ListAdapter<Harvest, HarvestAdapter.ViewHolder>(DIFF) {

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Harvest>() {
            override fun areItemsTheSame(a: Harvest, b: Harvest) = a.id == b.id
            override fun areContentsTheSame(a: Harvest, b: Harvest) = a == b
        }
    }

    inner class ViewHolder(val binding: ItemHarvestBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemHarvestBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val harvest = getItem(position)
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        holder.binding.apply {
            tvHiveName.text = harvest.hiveName
            tvQuantity.text = "${harvest.quantityKg} kg"
            tvDate.text = sdf.format(Date(harvest.harvestDate))
            tvQuality.text = "Quality: ${"⭐".repeat(harvest.qualityRating)}"
            if (harvest.notes.isNotEmpty()) {
                tvNotes.text = harvest.notes
                tvNotes.visibility = android.view.View.VISIBLE
            } else {
                tvNotes.visibility = android.view.View.GONE
            }
            btnDelete.setOnClickListener { onDelete(harvest) }
        }
    }
}
